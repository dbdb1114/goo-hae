package com.kdt.goohae.domain.user;


import lombok.Data;

@Data
public class OrderDetailVO {

    private int orderSeq;
    private int productCode;
    private int price;
    private int productEa;
    private int discount;
    private String deliStatus;
    private int payMoney;

}
