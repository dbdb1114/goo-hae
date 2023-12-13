package com.kdt.goohae.domain.user;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class OrderInfoVO {

    private int orderSeq;
    private String userId;
    private String receiverName;
    private String PhoneNumber;
    private int postNumber;
    private String address;
    private String orderDate;
    private String memo;
    private String imagePath;
    private int productCode;
    private int price;
    private int productEa;
    private String deliStatus;
    private String productName;

}
