package com.kdt.goohae.service.user;

import com.kdt.goohae.domain.admin.GetProductDTO;
import com.kdt.goohae.domain.user.WishVO;

import java.util.ArrayList;

public interface WishService {

    public ArrayList<GetProductDTO> selectList(String loginId);
    public int insert(WishVO vo);
    public int delete(WishVO vo);
    public int checkedDelete(WishVO vo);
}
