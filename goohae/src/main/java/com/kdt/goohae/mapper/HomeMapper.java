package com.kdt.goohae.mapper;


import com.kdt.goohae.domain.admin.CategoryVO;
import com.kdt.goohae.domain.admin.ProductImgVO;
import com.kdt.goohae.domain.admin.ProductVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HomeMapper {

    /* 카테고리 메뉴 가져오기 위한 메서드 */
    List<CategoryVO> selectList(Integer categoryCode);

    /* 디테일 페이지의 상품 정보 가져오기 */
    ProductVO selectOne(ProductVO vo);

    /* 디테일 페이지의 상품 이미지 가져오기 */
    List<ProductImgVO> getImages(ProductVO vo);

    /* 디테일 페이지 메인 이미지 가져오기 */
    ProductImgVO getMainImage(ProductVO vo);

    /* 디테일 페이지의 배너 이미지 가져오기 */
    ProductImgVO getBannerImage(ProductVO vo);

    /* 디테일 페이지의 상품 정보 이미지 가져오기 */
    ProductImgVO getInfoImage(ProductVO vo);

    /* 조회수 증가 */
    int updateCount(ProductVO vo);

}
