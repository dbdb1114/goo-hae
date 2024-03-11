package com.kdt.goohae.service.base;

import com.kdt.goohae.domain.product.CategoryDTO;
import java.util.ArrayList;


public interface CategoryService {
    ArrayList<CategoryDTO> selectList();
}
