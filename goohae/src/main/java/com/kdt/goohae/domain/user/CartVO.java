package com.kdt.goohae.domain.user;

import lombok.Data;

import java.util.List;

@Data
public class CartVO {

    String userId;
    String productCode;
    String productEa;
    String cartDate;
    List<Integer> productCodes;

}
