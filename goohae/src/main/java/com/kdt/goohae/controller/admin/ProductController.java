package com.kdt.goohae.controller.admin;


import com.kdt.goohae.domain.admin.ProductVO;
import com.kdt.goohae.domain.forPaging.PageMaker;
import com.kdt.goohae.domain.forPaging.SearchCri;
import com.kdt.goohae.domain.product.CategoryDTO;
import com.kdt.goohae.domain.product.OptionDTO;
import com.kdt.goohae.domain.product.ProductInfoDTO;
import com.kdt.goohae.service.admin.AdminCloudinaryService;
import com.kdt.goohae.service.admin.ProductService;
import com.kdt.goohae.service.base.CategoryService;
import java.util.ArrayList;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@Controller
@RequiredArgsConstructor
public class ProductController {

    // 필드
    private final ProductService service;
    private final AdminCloudinaryService cloudinaryService;
    private final CategoryService categoryService;
    // 생성자

    /**
     * 상품 등록 페이지를 위한 컨트롤러
     * @return 상품 등록 페이지
     */
    @GetMapping("/admin/reg-pro")
    public String regProductF(ModelAndView mv) {
        mv.setViewName("admin/regProduct");
        mv.addObject("category",categoryService.selectList());
        return "admin/regProduct";
    }

    @PostMapping("/admin/upload/detail-images")
    public Map<String,String> uploadDetailImages(
            @RequestParam("detailPageImages") ArrayList<MultipartFile> detailPageImages,
            @RequestParam("categoryName") String categoryName,
            @RequestParam("productName") String productName){
        Map<String, String> imgUrlList = cloudinaryService.uploadDetailImages(detailPageImages, categoryName, productName);
        return imgUrlList;
    }

    /***
     *
     * @param productInfoDTO
     * @param optionDTOList
     * @param request
     * @param mv
     * @return mv
     * @throws IOException
     */
    @PostMapping("/admin/reg-pro")
    public ModelAndView regProduct(@RequestParam("categoryDTO") CategoryDTO categoryDTO,
                                    @RequestParam("productInfo") ProductInfoDTO productInfoDTO,
                                    @RequestParam("productOptions") ArrayList<OptionDTO> optionDTOList,
                                    HttpServletResponse response, HttpServletRequest request,
                                    ModelAndView mv) throws IOException {

        String category = categoryDTO.getLargeCategory() + "/" + categoryDTO.getMediumCategory();
        String productName = productInfoDTO.getName();
        String regId = (String) request.getSession().getAttribute("adminId");
        productInfoDTO.setRegId(regId);
        int res;


        ArrayList<String> mainUplodImages = cloudinaryService.uploadMainImages(productInfoDTO.getMainImages(),category, productName);

        if(!optionDTOList.isEmpty()){
            for(OptionDTO option : optionDTOList){
                String url = cloudinaryService.uploadOptionImage(option.getImageFile(),category, productName);
                option.setImageUrl(url);
            }
            res = service.productUpload(productInfoDTO, mainUplodImages, optionDTOList);
        } else {
            res = service.productUpload(productInfoDTO, mainUplodImages);
        }

        if(res <= 0){
            mv.setViewName("/admin/regProduct");
            mv.addObject("message", "상품등록에 일부 오류가 있었습니다. 확인 후 재시도 바랍니다.");
        } else {
            mv.setViewName("/admin/main");
            mv.addObject("message", "상품등록이 완료되었습니다.");
        }

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
