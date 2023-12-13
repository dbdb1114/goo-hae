package com.kdt.goohae.mapper.user;

import com.kdt.goohae.domain.forPaging.SearchCri;
import com.kdt.goohae.domain.user.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface UserMapper {

    int insert(UserVO vo);
    UserVO selectOne(UserVO vo);
    int delete(UserVO vo);

    /* 유저 정보 가져오기 */
    ArrayList<UserVO> selectList(SearchCri cri);

    /* 유저 전체 데이터 갯수 */
    int getTotalData();
    int update(UserVO vo);
    String findId ( UserVO vo );
    int changePassword ( UserVO vo );
    int idCheck(UserVO vo);
}
