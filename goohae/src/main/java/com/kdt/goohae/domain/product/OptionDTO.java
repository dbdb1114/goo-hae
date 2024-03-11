package com.kdt.goohae.domain.product;

import lombok.Data;

@Data
public class OptionDTO {
    private int id;
    private int product_id;
    private String name;
    private int surcharge;
    private String image_url;
}
