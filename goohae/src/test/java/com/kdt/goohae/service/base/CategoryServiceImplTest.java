package com.kdt.goohae.service.base;

import static org.assertj.core.api.Assertions.assertThat;

import com.kdt.goohae.domain.product.CategoryDTO;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CategoryServiceImplTest {

    @Autowired CategoryService categoryService;

    @Test
    public void 카테고리_가져오기() {
        //given
        ArrayList<CategoryDTO> categoryDTOS = categoryService.selectList();

        //when
        for (CategoryDTO categoryDTO : categoryDTOS) {
            System.out.println(categoryDTO);
        }

        //then
        assertThat(categoryDTOS.size()).isEqualTo(12);
    }
}
