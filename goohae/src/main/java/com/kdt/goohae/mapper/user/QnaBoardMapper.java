package com.kdt.goohae.mapper.user;

import com.kdt.goohae.domain.forPaging.Criteria;
import com.kdt.goohae.domain.user.QnaBoardVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface QnaBoardMapper {

    ArrayList<QnaBoardVO> selectList(Criteria cri);
    ArrayList<QnaBoardVO> userList(String loginId);
    QnaBoardVO selectOne(QnaBoardVO vo);
    int insert(QnaBoardVO vo);
    int delete(QnaBoardVO vo);
    int update(QnaBoardVO vo);
    int getTotalData();
}
