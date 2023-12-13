package com.kdt.goohae.service.user;

import com.kdt.goohae.domain.admin.GetProductDTO;
import com.kdt.goohae.domain.user.CartVO;

import java.util.ArrayList;

public interface CartService {

    int insert(CartVO vo);
    int checkedInsert(CartVO vo);
    int delete(CartVO vo);
    int checkedDelete(CartVO vo);
    ArrayList<GetProductDTO> selectList(String loginId);
    int eaChange(CartVO vo);
    CartVO selectOne(CartVO vo);
}
