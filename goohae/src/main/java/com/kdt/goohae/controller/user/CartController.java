package com.kdt.goohae.controller.user;

import com.kdt.goohae.domain.user.CartVO;
import com.kdt.goohae.service.user.CartService;
//import com.sun.org.apache.xpath.internal.operations.Mod;
import com.kdt.goohae.service.user.WishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Controller
public class CartController {

    private final CartService cartService;
    private final WishService wishService;

    public CartController(CartService cartService,WishService wishService) {
        this.cartService = cartService;
        this.wishService = wishService;
    }

    /**
     * 내 장바구니
     *  mycart 페이지로 이동
     * loginId의 장바구니를 담아서 전달.
     * */
    @GetMapping(value = "logined-user/mycart")
    public String myCart(HttpSession httpSession, Model model){
        String loginId = (String) httpSession.getAttribute("loginId");
        model.addAttribute("userCart", cartService.selectList(loginId) );
        log.info("{}",cartService.selectList(loginId));
        return "user/myPage/shoppingCart";
    }

    /**
     * 장바구니의 상품을 삭제하고,
     * myCart 페이지로 이동
     * param : productCode
     * */
    @PostMapping(value = "logined-user/mycart/delete")
    public ModelAndView delete (CartVO vo, HttpSession httpSession, ModelAndView mv) {
        vo.setUserId((String) httpSession.getAttribute("loginId"));
        if ( cartService.delete(vo)>0 ){
            httpSession.setAttribute("message", "success");
        } else {
            httpSession.setAttribute("message", "fail");
        }
        mv.setViewName("/user/myPage/shoppingCart");
        mv.addObject("message","success");
        return mv;
    }

    /**
     * 체크된 상품들 카트에서 삭제
     * 삭제 후 shoppingCart로 이동
     * param : 체크된 상품들의 productCode로 이뤄진 배열
     * */
    @PostMapping(value = "logined-user/mycart/checked-delete")
    public String checkedDelete(CartVO vo, HttpSession httpSession) {
        vo.setUserId((String)httpSession.getAttribute("loginId"));
        if(cartService.checkedDelete(vo)>0){
            httpSession.setAttribute("message", "success");
        } else {
            httpSession.setAttribute("message", "fail");
        }
        return "redirect:logined-user/mycart/list";
    }

    /**
     * 장바구니 추가기능
     * 추가 결과는 model의 message에 담아서 전달
     * param : productCode
     * param : productEa
     * */
    @ResponseBody
    @PostMapping(value = "logined-user/mycart/insert")
    public Map<String, String> insert(CartVO vo, HttpSession httpSession){
        Map<String, String> map = new LinkedHashMap<>();

        if ( cartService.selectOne(vo) != null) {
            map.put("message", "duplicate");
        }
        vo.setUserId((String) httpSession.getAttribute("loginId") );
        if (cartService.insert(vo)>0){
            map.put("message","success");
        } else {
            map.put("message","fail");
        }
        return map;
    }

    @PostMapping(value = "logined-user/mycart/checked-insert")
    public ModelAndView checkedInsert (CartVO vo, HttpSession httpSession, ModelAndView mv){

        String loginId = (String) httpSession.getAttribute("loginId");

        if(vo.getProductCodes() == null) {
            mv.setViewName("/user/myPage/wishList");
            mv.addObject("wishList",wishService.selectList(loginId));
            return mv;
        } else {
            vo.setUserId((String) httpSession.getAttribute("loginId"));
            mv.setViewName("/user/myPage/shoppingCart");
            cartService.checkedInsert(vo);
            mv.addObject("userCart",cartService.selectList(loginId));
        }
        return mv;
    }

    @PostMapping(value = "logined-user/mycart/changeea")
    public String eaChange(CartVO vo,HttpSession httpSession, Model model){
        vo.setUserId((String) httpSession.getAttribute("loginId"));
        if( cartService.eaChange(vo)>0 ){
            model.addAttribute("changeEa", vo.getProductEa());
        }
        return "user/myPage/shoppingCart";
    }

}
