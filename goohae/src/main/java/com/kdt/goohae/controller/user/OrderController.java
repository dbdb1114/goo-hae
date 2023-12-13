package com.kdt.goohae.controller.user;

import com.kdt.goohae.domain.admin.GetProductDTO;
import com.kdt.goohae.domain.admin.ProductVO;
import com.kdt.goohae.domain.user.OrderDetailVO;
import com.kdt.goohae.domain.user.OrderInfoVO;
import com.kdt.goohae.domain.user.PaymentVO;
import com.kdt.goohae.domain.user.UserVO;
import com.kdt.goohae.service.admin.ProductService;
import com.kdt.goohae.service.user.OrderService;
import com.kdt.goohae.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;
    private final ProductService productService;

    public OrderController(OrderService orderService, UserService userService, ProductService productService) {
        this.orderService = orderService;
        this.userService = userService;
        this.productService = productService;
    }

    /**
     * OrderInfo에서 주문 일자 , 수량, 주문처리상태
     * Productdto로 이미지 경로, 이름, 옵션
     * */
    @GetMapping(value = "/logined-user/order/list")
    public ModelAndView selectList(ModelAndView mv, HttpSession httpSession){
        String loginId = (String)httpSession.getAttribute("loginId");
        mv.setViewName("user/myPage/myOrder");
        mv.addObject("orderList", orderService.selectList(loginId));
        return mv;
    }

    /**
     * 결제 화면으로 가는 것 ( 상품 상세 화면에서 )
     * param : productCode,
     * param : productPrice,
     * param : productEa,
     * */
    @GetMapping(value = "/logined-user/order/start")
    public ModelAndView orderPage(ModelAndView mv, ProductVO product, UserVO vo,
                                  HttpServletRequest request,
                                  @RequestParam(value = "pro_seq", required = false) int[] pro_seq){
        // 유저 정보 담기
        vo.setId((String)request.getSession().getAttribute("loginId"));
        mv.addObject("userInfo", userService.selectOne(vo));


        // 상품 정보 담기 ( 장바구니에서 넘어갔을 때 )
        if (pro_seq != null && pro_seq.length > 0) {
            List<GetProductDTO> listDto = new ArrayList<>();
            int totalP = 0;
            int totalD = 0;

            for (int i : pro_seq) {
                ProductVO vos = new ProductVO();
                vos.setProductCode(i);

                GetProductDTO dtos = productService.getProductOne(vos);

                listDto.add(dtos);
                totalP += dtos.getPrice();

                if (dtos.getDiscount() > 0) {
                    totalD += dtos.getPrice() - dtos.getDiscount() / 100 * dtos.getPrice();
                }
            }

            mv.addObject("products", listDto);
            mv.addObject("totalPrice", totalP);
            mv.addObject("totalDiscount", totalD);

            // 단일 상품인 경우 ( 주문하기에서 바로 넘어갔을 때 )
        } else {
            GetProductDTO dto = productService.getProductOne(product);
            dto.setProductEa(product.getProductEa());

            mv.addObject("products", dto);

            int totalPrice = dto.getPrice() * dto.getProductEa();
            int discount = 0;

            if (dto.getDiscount() > 0) {
//                totalPrice = (dto.getPrice() - (int)(dto.getDiscount() / 100.00 * dto.getPrice())) * dto.getProductEa();
                discount = (int)(dto.getDiscount() / 100.00 * dto.getPrice()) * dto.getProductEa();
            }

            mv.addObject("totalPrice", totalPrice);
            mv.addObject("totalDiscount", discount);
        }

        mv.setViewName("user/memberPayment");
        return mv;
    }


    /**
     * 주문하기를 위한 컨트롤러
     * @param vo OrderInfoVO
     * @param detail OrderDetailVO
     * @return
     */
    @ResponseBody
    @PostMapping("/logined-user/order/insert")
    public Map<String, Object> insertOrder(OrderInfoVO vo, OrderDetailVO detail) {
        Map<String, Object> map = new LinkedHashMap<>();

        if (orderService.insertOrder(vo) > 0) {
            vo = orderService.getOrderInfo(vo);

            /* 주문 상세에 데이터 넣기 */
            detail.setOrderSeq(vo.getOrderSeq());

            if (orderService.insertDetail(detail) < 1) {
                map.put("message", "error");
                return map;
            }

            // 주문 정보 담기
            map.put("info", vo);


            List<OrderDetailVO> list = orderService.getOrderDetail(vo);
            int totalPrice = 0;

            for (OrderDetailVO v : list) {
                totalPrice += v.getPrice() - v.getDiscount() / 100 * v.getPrice();
            }

            map.put("total", totalPrice);
            map.put("message", "success");
        } else {
            map.put("message", "error");
        }

        return map;
    }



    /**
     * 한 개의 상품 주문시
     * param : receiverName
     * param : address
     * param : phoneNumber
     * param : postNumber
     * param : productCode
     * param : productEa
     * param : price
     * */
    // /logined-user/order/payment
    @PostMapping(value = "/logined-user/order/payment")
    public ModelAndView pay(ModelAndView mv, PaymentVO vo, HttpServletRequest request){
        String uri = "user/paymentComplete";

        if (orderService.insertPay(vo) > 0) {
            // 유저 정보 담기
            UserVO user = new UserVO();
            user.setId((String)request.getSession().getAttribute("loginId"));

            mv.addObject("user", userService.selectOne(user));

            // 결제 정보 담기
            mv.addObject("pay", orderService.getPay(vo));

            // 주문 정보 담기
            OrderInfoVO ovo = orderService.getOrder(vo);
            mv.addObject("orderInfo", ovo);

            // 주문 상세 담기
            List<OrderDetailVO> dvo = orderService.getOrderDetail(ovo);
            mv.addObject("orderDetail", dvo);

            // 상품 정보 담기 ( 나중에 여러 상품 주문 시 고려해서 수정 예정 )
            ProductVO pvo = new ProductVO();
            pvo.setProductCode(dvo.get(0).getProductCode());

            GetProductDTO dto = productService.getProductOne(pvo);
            dto.setProductEa(dvo.get(0).getProductEa());
            mv.addObject("productInfo", dto);

            mv.addObject("total", dto.getPrice() * dto.getProductEa());

            int disPrice = 0;
            if (dto.getDiscount() > 0) {
                disPrice = (int)(dto.getDiscount() / 100.00 * dto.getPrice()) * dto.getProductEa();
            }
            mv.addObject("disPrice", disPrice);
            mv.addObject("disTotalPrice", (dto.getPrice() - (int)(dto.getDiscount() / 100.00 * dto.getPrice())) * dto.getProductEa());

            // 메시지 담기
            mv.addObject("message", "success");
        } else {
            uri = "redirect:/";
            mv.addObject("message", "error");
        }

        mv.setViewName(uri);
        return mv;
    }

}
