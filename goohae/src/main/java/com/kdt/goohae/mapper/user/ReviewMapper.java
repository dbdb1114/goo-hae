package com.kdt.goohae.mapper.user;

import com.kdt.goohae.domain.user.ReviewVO;
import com.kdt.goohae.domain.user.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface ReviewMapper {

    ArrayList<ReviewVO> getUserReview(String loginId);
    ArrayList<ReviewVO> getProductReview(int productCode);
    int insert(ReviewVO vo);
    int delete(ReviewVO vo);
}
