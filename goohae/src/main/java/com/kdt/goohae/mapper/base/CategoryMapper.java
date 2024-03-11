package com.kdt.goohae.mapper.base;

import com.kdt.goohae.domain.product.CategoryDTO;
import java.util.ArrayList;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper {
    ArrayList<CategoryDTO> selectList();
}
