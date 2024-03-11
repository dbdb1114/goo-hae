package com.kdt.goohae.domain.product;


import java.util.ArrayList;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProductInfoDTO {
    private int id;
    private int categoryId;
    private String regId;
    private String name;
    private int price;
    private String detail;
    private int discount;
    private String upload_date;
    private int view_count;
    private ArrayList<MultipartFile> mainImages;
}
