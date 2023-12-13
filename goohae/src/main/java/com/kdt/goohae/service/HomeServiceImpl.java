package com.kdt.goohae.service;


import com.kdt.goohae.controller.HomeController;
import com.kdt.goohae.domain.admin.CategoryVO;
import com.kdt.goohae.domain.admin.ProductImgVO;
import com.kdt.goohae.domain.admin.ProductVO;
import com.kdt.goohae.mapper.HomeMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeServiceImpl implements HomeService {

    // 필드
    private final HomeMapper mapper;

    // 생성자
    public HomeServiceImpl(HomeMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * 해당 카테고리 가져오기 위한 메서드
     * @param categoryCode 카테고리 코드
     * @return CategoryVO List
     */
    @Override
    public List<CategoryVO> selectList(Integer categoryCode) {
        return mapper.selectList(categoryCode);
    }


    /**
     * 디테일 페이지의 상품 정보 가져오기
     * @param vo ProductVO
     * @return ProductVO
     */
    @Override
    public ProductVO selectOne(ProductVO vo) {
        return mapper.selectOne(vo);
    }


    /**
     * 디테일 페이지의 상품 이미지들 가져오기
     * @param vo ProductVO
     * @return ProductVO
     */
    @Override
    public List<ProductImgVO> getImages(ProductVO vo) {
        return mapper.getImages(vo);
    }


    /**
     * 디테일 페이지 메인 이미지 가져오기
     * @param vo ProductVO
     * @return ProductVO
     */
    @Override
    public ProductImgVO getMainImage(ProductVO vo) {
        return mapper.getMainImage(vo);
    }


    /**
     * 디테일 페이지 배너 이미지 가져오기
     * @param vo ProductVO
     * @return ProductVO
     */
    @Override
    public ProductImgVO getBannerImage(ProductVO vo) {
        return mapper.getBannerImage(vo);
    }


    /**
     * 디테일 페이지 상품 정보 이미지 가져오기
     * @param vo ProductVO
     * @return ProductVO
     */
    @Override
    public ProductImgVO getInfoImage(ProductVO vo) {
        return mapper.getInfoImage(vo);
    }


    /**
     * 디테일 클릭 시 조회수 증가
     * @param vo ProductVO
     * @return 성공 시 1, 실패 시 0
     */
    @Override
    public int updateCount(ProductVO vo) {
        return mapper.updateCount(vo);
    }
}
