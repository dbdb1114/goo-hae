package com.kdt.goohae.controller.user;

import com.kdt.goohae.domain.admin.CategoryVO;
import com.kdt.goohae.domain.forPaging.PageMaker;
import com.kdt.goohae.domain.forPaging.SearchCri;
import com.kdt.goohae.service.admin.ProductService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductPageController {
    // 할 일
    ProductService productService;

    public ProductPageController(ProductService productService) {this.productService = productService; }

    @GetMapping(value = "/subpage")
    public ModelAndView subPage( ModelAndView mv, SearchCri cri,PageMaker pageMaker,
                                 @RequestParam("categoryCode") int categoryCode ){
        cri.setStartNum();

        // 카테고리검색을 위해 cri에 채워넣음
        cri.setCategoryCode(categoryCode);

        // 상품 데이터 가져오기
        mv.addObject("productList", productService.getProduct(cri));

        //페이징 정보 넣기
        mv.addObject("pageMaker", pageMaker);
        mv.setViewName("/product/subPage");

        return mv;
    }
    @GetMapping(value = "/product-detail")
    public String detail() {return "product/detailPage";}

}
