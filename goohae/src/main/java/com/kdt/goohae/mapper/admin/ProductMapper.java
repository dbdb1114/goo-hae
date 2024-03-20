package com.kdt.goohae.mapper.admin;


import com.kdt.goohae.domain.admin.CategoryVO;
import com.kdt.goohae.domain.admin.GetProductDTO;
import com.kdt.goohae.domain.admin.ProductImgVO;
import com.kdt.goohae.domain.admin.ProductVO;
import com.kdt.goohae.domain.forPaging.SearchCri;
import com.kdt.goohae.domain.product.OptionDTO;
import com.kdt.goohae.domain.product.ProductInfoDTO;
import java.util.ArrayList;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {

    /* 상품 등록 */
    int regProduct(ProductVO vo);

    /* 상품 이미지 등록 */
    int regProductImg(ProductImgVO vo);

    /* 상품 삭제 */
    int deleteProduct(ProductVO vo);

    /* 상품 정보 저장 */
    int regProductInfo(ProductInfoDTO productInfoDTO);

    /* 옵션 정보 저장*/
    int regProductOption(ArrayList<OptionDTO> optionDTOList);

    /* 상품 매인 이미지 저장 */
    int regProductImage(ArrayList<String> productImages);

    /* 상품 데이터 가져오기 전 카테고리 코드 가져오기 */
    int getCategory(CategoryVO vo);

    /* 카테고리별 상품 데이터 전송 ( 페이지에 보이는 상품을 위한 ) */
    List<GetProductDTO> getProduct(SearchCri cri);

    /* 데이터 전체 갯수 조회 */
    int getTotalData(SearchCri cri);



    /* 전체 검색( 헤더의 검색바 ) */
    List<GetProductDTO> getSearchProduct(SearchCri cri);
    int getSearchTotalData(SearchCri cri);



    /* 메인 페이지 상품 데이터 가져오기 */
    /* new product */
    List<GetProductDTO> getMainNew();

    /* 메인 페이지 상품 데이터 가져오기 2 */
    /* new product */
    List<GetProductDTO> getMainNewTwo();

    /* best product */
    List<GetProductDTO> getMainBest();


    /* 상품 단일 정보 가져오기 */
    GetProductDTO getProductOne(ProductVO vo);

}
