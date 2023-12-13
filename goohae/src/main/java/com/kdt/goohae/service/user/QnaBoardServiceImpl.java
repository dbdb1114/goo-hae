package com.kdt.goohae.service.user;

import com.kdt.goohae.domain.forPaging.Criteria;
import com.kdt.goohae.domain.user.QnaBoardVO;
import com.kdt.goohae.domain.user.UserVO;
import com.kdt.goohae.mapper.user.QnaBoardMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class QnaBoardServiceImpl implements QnaBoardService{


    QnaBoardMapper mapper;

    public QnaBoardServiceImpl(QnaBoardMapper mapper) {this.mapper = mapper;}
    @Override
    public ArrayList<QnaBoardVO> selectlList(Criteria cri) {return mapper.selectList(cri);}
    @Override
    public ArrayList<QnaBoardVO> userList(String loginId) {return mapper.userList(loginId);}

    @Override
    public QnaBoardVO selectOne(QnaBoardVO vo) {return mapper.selectOne(vo);}

    @Override
    public int insert(QnaBoardVO vo) { return mapper.insert(vo); }

    @Override
    public int delete(QnaBoardVO vo) {return mapper.delete(vo);}

    @Override
    public int update(QnaBoardVO vo) { return mapper.update(vo); }

    public int getTotalData(){return mapper.getTotalData();}
}
