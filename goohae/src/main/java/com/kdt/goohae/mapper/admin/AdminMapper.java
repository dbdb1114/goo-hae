package com.kdt.goohae.mapper.admin;


import com.kdt.goohae.domain.admin.ManagerVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper {

    /* 로그인을 위한 메서드 */
    ManagerVO selectOne(ManagerVO vo);

}
