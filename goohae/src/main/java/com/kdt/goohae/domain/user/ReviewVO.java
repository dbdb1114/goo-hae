package com.kdt.goohae.domain.user;

import lombok.Data;

@Data
public class ReviewVO {

    private String userId;
    private String productName;
    private int productCode;
    private String title;
    private String content;
    private int reviewSeq;

}
