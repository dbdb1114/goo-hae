package com.kdt.goohae.domain.user;


import lombok.Data;

@Data
public class PaymentVO {

    private int paymentSeq;
    private int orderSeq;
    private int totalPrice;
    private String depositor;
    private String depositBank;
    private String paymentDate;
    private int payMoney;

}
