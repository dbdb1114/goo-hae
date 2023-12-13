package com.kdt.goohae.service.user;

import com.kdt.goohae.domain.user.ReviewVO;
import com.kdt.goohae.mapper.user.ReviewMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ReviewServiceImpl implements ReviewService {

    ReviewMapper mapper;

    public ReviewServiceImpl(ReviewMapper mapper) { this.mapper = mapper; }

    @Override
    public ArrayList<ReviewVO> getUserReview(String loginId) { return mapper.getUserReview(loginId); }
    public ArrayList<ReviewVO> getProductReview(int productCode) { return mapper.getProductReview(productCode); }
    @Override
    public int insert(ReviewVO vo) { return mapper.insert(vo); }

    @Override
    public int delete(ReviewVO vo) { return mapper.delete(vo); }
}
