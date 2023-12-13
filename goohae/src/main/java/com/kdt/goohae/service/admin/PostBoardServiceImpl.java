package com.kdt.goohae.service.admin;


import com.kdt.goohae.domain.admin.PostBoardVO;
import com.kdt.goohae.domain.forPaging.SearchCri;
import com.kdt.goohae.mapper.admin.PostBoardMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostBoardServiceImpl implements PostBoardService {

    // 필드
    private final PostBoardMapper mapper;

    // 생성자
    public PostBoardServiceImpl(PostBoardMapper mapper) {
        this.mapper = mapper;
    }


    /**
     * 게시글 등록을 위한 메서드
     * @param vo PostBoardVO
     * @return 성공 시 1, 실패 시 0
     */
    @Override
    public int insert(PostBoardVO vo) {
        return mapper.insert(vo);
    }


    /**
     * 게시글 데이터를 가져오기 위한 메서드
     * @param cri SearchCri
     * @return 게시글 데이터 List
     */
    @Override
    public List<PostBoardVO> selectList(SearchCri cri) {
        return mapper.selectList(cri);
    }


    /**
     * 게시글 전체 데이터 갯수를 위한 메서드
     * @return 게시글 전체 데이터 갯수
     */
    @Override
    public int getTotalData() {
        return mapper.getTotalData();
    }


    /**
     * 게시글 삭제를 위한 메서드
     * @param vo PostBoardVO
     * @return 성공 시 1, 실패 시 0
     */
    @Override
    public int delete(PostBoardVO vo) {
        return mapper.delete(vo);
    }


    /**
     * 게시글 디테일을 위한 메서드
     * @param vo PostBoardVO
     * @return PostBoardVO
     */
    @Override
    public PostBoardVO selectOne(PostBoardVO vo) {
        return mapper.selectOne(vo);
    }


    /**
     * 게시글 수정을 위한 메서드
     * @param vo PostBoardVO
     * @return 성공 시 1, 실패 시 0
     */
    @Override
    public int update(PostBoardVO vo) {
        return mapper.update(vo);
    }
}
