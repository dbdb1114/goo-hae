package com.kdt.goohae.mapper.user;

import com.kdt.goohae.domain.admin.GetProductDTO;
import com.kdt.goohae.domain.user.CartVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface CartMapper {
    int insert(CartVO vo);
    int checkedInsert(CartVO vo);
    int delete(CartVO vo);
    int checkedDelete(CartVO vo);
    ArrayList<GetProductDTO> selectList(String loginId);
    int eaChange(CartVO vo);

    CartVO selectOne(CartVO vo);

}
