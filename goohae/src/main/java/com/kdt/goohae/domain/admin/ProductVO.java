package com.kdt.goohae.domain.admin;


import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ProductVO {

    private int categoryCode;
    private String managerId;
    private int productCode;
    private String productName;
    private int price;
    private String productDetail;
    private int discount;
    private int viewCount;

    // 파일 객체를 위한 필드
    private List<MultipartFile> files;

    // 상품 갯수 데이터 전송을 위한 필드
    private int productEa;

}
