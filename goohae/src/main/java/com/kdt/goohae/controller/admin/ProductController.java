package com.kdt.goohae.controller.admin;


import com.kdt.goohae.domain.admin.CategoryVO;
import com.kdt.goohae.domain.admin.ProductImgVO;
import com.kdt.goohae.domain.admin.ProductVO;
import com.kdt.goohae.domain.forPaging.PageMaker;
import com.kdt.goohae.domain.forPaging.SearchCri;
import com.kdt.goohae.service.admin.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@Controller
public class ProductController {

    // 필드
    private final ProductService service;

    // 생성자
    public ProductController(ProductService service) {
        this.service = service;
    }

    /**
     * 상품 등록 페이지를 위한 컨트롤러
     * @return 상품 등록 페이지
     */
    @GetMapping("/admin/reg-pro")
    public String regProductF() {
        return "admin/regProduct";
    }


    /**
     * 상품 등록 및 상품 이미지 등록을 위한 컨트롤러
     * @param vo ProductVO
     * @param img_vo ProductImgVO
     * @param request HttpServletRequest
     * @param mv ModelAndView
     * @return 메시지와 함께 다시 상품페이지로
     * @throws IOException
     */
    @PostMapping("/admin/reg-pro")
    public ModelAndView regProduct(ProductVO vo, ProductImgVO img_vo, HttpServletRequest request, ModelAndView mv) throws IOException {
        vo.setManagerId((String)request.getSession().getAttribute("adminID"));

        // 상품 테이블에 등록 성공 시
        if (service.regProduct(vo) > 0) {
            List<MultipartFile> files = vo.getFiles();

            img_vo.setProductName(vo.getProductName());
            img_vo.setProductOption(vo.getProductOption());
            img_vo.setCategoryCode(vo.getCategoryCode());

            int fileNum = 0;

            for (MultipartFile m : files) {
                fileNum++;

                if (service.regProductImg(img_vo, m, request, fileNum) < 1) {
                    mv.addObject("message", "이미지 등록에 실패하였습니다.");
                    mv.setViewName("admin/regProduct");
                    return mv;
                }
            }

            mv.addObject("message", "등록을 완료하였습니다.");
            // 상품 테이블에 등록 실패 시
        } else {
            mv.addObject("message", "상품 등록에 실패하였습니다.");
        }
        mv.setViewName("admin/regProduct");
        return mv;
    }


    /**
     * 상품 리스트 페이지를 위한 컨트롤러
     * @param model 상품에 대한 List 객체와 페이징에 대한 객체가 들어갈 Model
     * @return 상품 리스트 페이지
     */
    @GetMapping("/admin/get-pro")
    public String productList(Model model, PageMaker pageMaker, SearchCri cri,
                              @RequestParam("categoryCode") int categoryCode,
                              @RequestParam(value = "message", required = false) String message)  {
        cri.setStartNum();

        // cri에 카테고리 검색을 위한 필드 채워넣기
        cri.setCategoryCode(categoryCode);

        // 상품 데이터 가져오기
        model.addAttribute("product", service.getProduct(cri));

        // 페이징을 위한 정보들 넣기
        model.addAttribute("code", categoryCode);
        model.addAttribute("check", cri.getCheck());

        // 페이징을 위한 객체에 cri 필드 setter로 채우기 및 전체 데이터 갯수 채우기
        pageMaker.setCriteria(cri);
        pageMaker.setTotalDataCount(service.getTotalData(cri));

        model.addAttribute("pageMake", pageMaker);

        // 상품 삭제 시 메시지 전송을 위한 조건문
        if (message != null && message.length() > 0) {
            model.addAttribute("message", message);
        }

        return "admin/productList";
    }


    /**
     * 상품 삭제를 위한 컨트롤러
     * @param vo ProductVO
     * @param mv ModelAndView
     * @param rttr RedirectAttributes
     * @return 메시지와 함께 리다이렉트
     */
    @GetMapping("/admin/del-pro")
    public ModelAndView delProduct(ProductVO vo, ModelAndView mv, RedirectAttributes rttr) {
        String uri = "redirect:/admin/get-pro";

        if (service.deleteProduct(vo) > 0) {
            rttr.addAttribute("message", "삭제에 성공하였습니다.");
        } else {
            rttr.addAttribute("message", "삭제에 실패하였습니다.");
        }

        rttr.addAttribute("check", "new");
        rttr.addAttribute("categoryCode", 1);
        mv.setViewName(uri);
        return mv;
    }



}
