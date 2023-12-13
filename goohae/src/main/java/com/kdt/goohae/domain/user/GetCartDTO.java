package com.kdt.goohae.domain.user;

import lombok.Data;

@Data
public class GetCartDTO {

    private int productCode;
    private int productEa;
    private String imagePath;
    private String productName;
    private int price;

}
