package com.kdt.goohae.service.user;

import com.kdt.goohae.domain.admin.GetProductDTO;
import com.kdt.goohae.domain.user.CartVO;
import com.kdt.goohae.domain.user.PaymentVO;
import com.kdt.goohae.mapper.user.CartMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class CartServiceImple implements CartService {

    CartMapper mapper;

    public CartServiceImple(CartMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public int insert(CartVO vo) {return mapper.insert(vo);}
    public int checkedInsert(CartVO vo) {return mapper.checkedInsert(vo);}
    @Override
    public int delete(CartVO vo) {return mapper.delete(vo);}
    @Override
    public int checkedDelete(CartVO vo) {return mapper.checkedDelete(vo);}
    @Override
    public ArrayList<GetProductDTO> selectList(String loginId) {return mapper.selectList(loginId);}
    public int eaChange(CartVO vo){return mapper.eaChange(vo);}
    public CartVO selectOne(CartVO vo){return mapper.selectOne(vo);}
}
