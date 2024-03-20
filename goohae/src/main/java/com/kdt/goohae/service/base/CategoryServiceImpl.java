package com.kdt.goohae.service.base;

import com.kdt.goohae.domain.product.CategoryDTO;
import com.kdt.goohae.mapper.base.CategoryMapper;
import java.util.ArrayList;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService{
    CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    @Override
    public ArrayList<CategoryDTO> selectList() {
        return categoryMapper.selectList();
    }
}
