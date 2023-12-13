package com.kdt.goohae.controller.user;

import com.kdt.goohae.domain.user.ReviewVO;
import com.kdt.goohae.service.user.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class ReviewController {

    ReviewService reviewService;

    public ReviewController(ReviewService reviewService) { this.reviewService = reviewService; }

    /**
     *  주문 기록 있는지 확인후 있으면 insert 실행
     *  param : title ( review 타이틀 )
     *  param : content ( review 내용 )
     *  param : productCode ( 상품 코드 )
     *  loaction.reload()
     * */
    @PostMapping(value = "logined-user/review/insert")
    public void insert(ReviewVO vo, HttpSession httpSession, HttpServletRequest httpServletRequest) {
        vo.setUserId((String) httpSession.getAttribute("loginId"));
//        if ( orderService.getUser ){
//          주문기록 있는지 확인
//        }else {
//          httpSession.setAttribute("message","주문기록 없음");
//          return
//        }
        if (reviewService.insert(vo) > 0){
            httpSession.setAttribute("message", "success");
        } else {
            httpSession.setAttribute("message", "fail");
        }
    }

    /**
     * 리뷰 삭제 메서드
     * param : reviewSeq
     * */
    @GetMapping(value = "logined-user/review/delete")
    public String delete (ReviewVO vo, HttpSession httpSession){
        vo.setUserId( (String) httpSession.getAttribute("loginId"));
        if( reviewService.delete(vo)>0 ){
            httpSession.setAttribute("message", "succcess");
        } else {
            httpSession.setAttribute("message", "fail");
        }
        return "redirect:user/myPage/myPost";
    }

}
