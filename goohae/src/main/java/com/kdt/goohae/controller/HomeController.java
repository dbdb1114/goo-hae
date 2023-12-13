package com.kdt.goohae.controller;


import com.kdt.goohae.domain.admin.ProductVO;
import com.kdt.goohae.domain.forPaging.PageMaker;
import com.kdt.goohae.domain.forPaging.SearchCri;
import com.kdt.goohae.service.HomeService;
import com.kdt.goohae.service.admin.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Slf4j
@Controller
public class HomeController {

    // 필드
    private final HomeService service;
    private final ProductService productService;

    // 생성자
    public HomeController(HomeService service, ProductService productService) {
        this.service = service;
        this.productService = productService;
    }

    /**
     * 메인페이지를 위한 컨트롤러
     * @param model Model
     * @return 신상품, 베스트 상품과 함께 메인페이지로
     */
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("newProduct", productService.getMainNew());
        model.addAttribute("newProductTwo", productService.getMainNewTwo());
        model.addAttribute("bestProduct", productService.getMainBest());
        return "index";
    }


    /**
     * 서브 페이지를 위한 컨트롤러
     * @param model Model
     * @return
     */
    @GetMapping("/sub")
    public String sub(Model model, SearchCri cri, PageMaker pageMaker) {
        String cate;
        String subHeadImg = "/images/staticImages/";
        int code = cri.getCategoryCode();

        if (code < 5) {
            cate = "거실";
            subHeadImg += "livingTitle.png";
        } else if (code < 8) {
            cate = "침실";
            subHeadImg += "BedroomTitle.png";
        } else if (code < 10) {
            cate = "주방";
            subHeadImg += "DiningTitle.png";
        } else {
            cate = "드레스룸";
            subHeadImg += "DressroomTitle.png";
        }
        model.addAttribute("headText", cate);
        model.addAttribute("headImg", subHeadImg);

        model.addAttribute("cate", service.selectList(cri.getCategoryCode()));

        cri.setStartNum();

        // 상품 데이터 넣기
        model.addAttribute("product", productService.getProduct(cri));

        // 페이징을 위한 정보들 넣기
        model.addAttribute("code", code);
        model.addAttribute("check", cri.getCheck());

        // 페이징을 위한 객체에 cri 필드 setter로 채우기 및 전체 데이터 갯수 채우기
        pageMaker.setCriteria(cri);
        pageMaker.setTotalDataCount(productService.getTotalData(cri));
        model.addAttribute("pageMake", pageMaker);

        return "product/subPage";
    }


    /**
     * 디테일 페이지를 위한 컨트롤러
     * @param vo ProductVO
     * @param mv ModelAndView
     * @return 디테일 페이지
     */
    @GetMapping("/detail")
    public ModelAndView detail(ProductVO vo, ModelAndView mv) {
        String uri = "product/detailPage";

        /* 상품 정보 */
        mv.addObject("product", service.selectOne(vo));

        /* 상품 이미지들 */
        mv.addObject("images", service.getImages(vo));

        /* 상품 메인 이미지 */
        mv.addObject("main", service.getMainImage(vo));

        /* 상품 배너 이미지 */
        mv.addObject("banner", service.getBannerImage(vo));

        /* 상품 정보 이미지 */
        mv.addObject("info", service.getInfoImage(vo));

        if (service.updateCount(vo) < 1) {
            uri = "refirect:/";
        }

        mv.setViewName(uri);
        return mv;
    }


    /**
     * 검색 페이지를 위한 컨트롤러
     * @param cri SearchCri
     * @param pageMaker PageMaker
     * @param model Model
     * @return 검색 페이지
     */
    @GetMapping("/search")
    public String searchPage(SearchCri cri, PageMaker pageMaker, Model model) {
        cri.setStartNum();
        cri.setRowsPerPage(12);

        model.addAttribute("keyword", cri.getKeyword());
        model.addAttribute("product", productService.getSearchProduct(cri));

        pageMaker.setCriteria(cri);
        pageMaker.setTotalDataCount(productService.getSearchTotalData(cri));
        model.addAttribute("pageMake", pageMaker);

        return "product/searchPage";
    }

}
