package com.kdt.goohae.service.user;

import com.kdt.goohae.domain.user.ReviewVO;

import java.util.ArrayList;

public interface ReviewService {

     ArrayList<ReviewVO> getUserReview(String loginId);
     ArrayList<ReviewVO> getProductReview(int porductCode);
     int insert(ReviewVO vo);

     int delete(ReviewVO vo);
}
