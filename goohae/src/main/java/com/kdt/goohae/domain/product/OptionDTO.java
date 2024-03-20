package com.kdt.goohae.domain.product;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class OptionDTO {
    private int id;
    private int productId;
    private String name;
    private int surcharge;
    private String imageUrl;
    private MultipartFile imageFile;
}
