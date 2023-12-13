package com.kdt.goohae.domain.user;

import lombok.Data;

import java.util.List;

@Data
public class WishVO {

    private String userId;
    private int productCode;
    private List<Integer> productCodes;

}
