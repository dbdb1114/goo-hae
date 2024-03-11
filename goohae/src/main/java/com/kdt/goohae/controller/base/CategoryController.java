package com.kdt.goohae.controller.base;

import com.kdt.goohae.domain.product.CategoryDTO;
import com.kdt.goohae.service.base.CategoryService;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/category/list")
    public ArrayList<CategoryDTO> getCategory(){
        return categoryService.selectList();
    }

}
