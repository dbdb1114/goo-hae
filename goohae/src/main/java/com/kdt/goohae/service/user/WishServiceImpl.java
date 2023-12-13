package com.kdt.goohae.service.user;

import com.kdt.goohae.domain.admin.GetProductDTO;
import com.kdt.goohae.domain.user.WishVO;
import com.kdt.goohae.mapper.user.WishMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class WishServiceImpl implements WishService{

    private final WishMapper wishMapper;
    public WishServiceImpl(WishMapper wishMapper) {
        this.wishMapper = wishMapper;
    }

    public ArrayList<GetProductDTO> selectList(String loginId){return wishMapper.selectList(loginId);}
    public int insert(WishVO vo){
        return wishMapper.insert(vo);
    }
    public int delete(WishVO vo) { return wishMapper.delete(vo); }
    public int checkedDelete(WishVO vo) {return wishMapper.checkedDelete(vo);}
}
