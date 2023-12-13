package com.kdt.goohae.service.admin;

import com.kdt.goohae.domain.admin.PostBoardVO;
import com.kdt.goohae.domain.forPaging.SearchCri;

import java.util.List;

public interface PostBoardService {

    /* 게시글 등록을 위한 메서드 */
    int insert(PostBoardVO vo);

    /* 게시글 데이터를 가져오기 위한 메서드 */
    List<PostBoardVO> selectList(SearchCri cri);

    /* 게시글 전체 데이터 갯수 */
    int getTotalData();

    /* 게시글 삭제를 위한 메서드 */
    int delete(PostBoardVO vo);

    /* 게시글 디테일을 위한 메서드 */
    PostBoardVO selectOne(PostBoardVO vo);

    /* 게시글 수정을 위한 메서드 */
    int update(PostBoardVO vo);

}
