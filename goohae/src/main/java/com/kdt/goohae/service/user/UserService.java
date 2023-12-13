package com.kdt.goohae.service.user;

import com.kdt.goohae.domain.forPaging.SearchCri;
import com.kdt.goohae.domain.user.UserVO;

import java.util.ArrayList;

public interface UserService {

    int insert(UserVO vo);
    UserVO selectOne(UserVO vo);
    int delete (UserVO vo);
    /* 유저 정보 가져오기 */
    ArrayList<UserVO> selectList(SearchCri cri);
    /* 유저 전체 데이터 갯수 */
    int getTotalData();
    int update(UserVO vo);
    String findId(UserVO vo);
    int changePassword(UserVO vo);
    int idCheck(UserVO vo);

}

