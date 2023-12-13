package com.kdt.goohae.mapper.user;

import com.kdt.goohae.domain.admin.GetProductDTO;
import com.kdt.goohae.domain.user.WishVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface WishMapper {
    public ArrayList<GetProductDTO> selectList(String loginId);
    public int insert(WishVO vo);
    public int delete(WishVO vo);
    public int checkedDelete(WishVO vo);
}
