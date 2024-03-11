package com.kdt.goohae.domain.product;

import lombok.Data;

@Data
public class CategoryDTO {
    private int id;
    private String largeCategory;
    private int lgId;
    private String mediumCategory;
    private int mdId;
}
