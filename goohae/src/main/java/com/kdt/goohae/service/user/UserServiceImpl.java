package com.kdt.goohae.service.user;

import com.kdt.goohae.domain.forPaging.SearchCri;
import com.kdt.goohae.domain.user.UserVO;
import com.kdt.goohae.mapper.user.UserMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService{

    UserMapper mapper;
    public UserServiceImpl(UserMapper mapper) {this.mapper = mapper;}

    /**
     * 회원가입 메서드
     * */
    @Override
    public int insert(UserVO vo) {
        return mapper.insert(vo);
    }
    /**
     * 유저 디테일 정보
     * */
    @Override
    public UserVO selectOne(UserVO vo) {return mapper.selectOne(vo);}
    /**
     * 유저 삭제
     * */
    @Override
    public int delete(UserVO vo) {return mapper.delete(vo);}
    /**
     * 유저 리스트
     * */
    @Override
    public ArrayList<UserVO> selectList(SearchCri cri) { return mapper.selectList(cri);}
    /**
     * 총 유저의 수
     * */
    @Override
    public int getTotalData() { return mapper.getTotalData(); }
    /**
     * 회원정보 수정
     * */
    public int update(UserVO vo) { return mapper.update(vo); }
    /**
     * id찾기
     * */
    public String findId(UserVO vo) {return mapper.findId(vo);}
    /**
     * 비밀번호 변경
     * */
    public int changePassword(UserVO vo) {return mapper.changePassword(vo);}
    /**
     * 아이디 중복체크
     * */
    public int idCheck(UserVO vo){return mapper.idCheck(vo);}

}
