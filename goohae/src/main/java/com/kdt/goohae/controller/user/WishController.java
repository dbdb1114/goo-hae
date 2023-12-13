package com.kdt.goohae.controller.user;

import com.kdt.goohae.domain.user.WishVO;
import com.kdt.goohae.service.user.WishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class WishController {

    WishService wishService;

    public WishController(WishService wishService) {this.wishService = wishService;}

    /**
     * myWishList로 이동
     * */
    @GetMapping(value = "logined-user/mywish")
    public ModelAndView myWish(ModelAndView mv, HttpSession httpSession) {
        mv.setViewName("user/myPage/wishList");
        return mv.addObject("wishList",wishService.selectList((String) httpSession.getAttribute("loginId")));
    }

    /**
     * wishList에서 상품 삭제
     * param : productCode
     * */
    @GetMapping(value = "logined-user/mywish/delete")
    public String delete (WishVO vo, HttpSession httpSession){
        vo.setUserId((String) httpSession.getAttribute("loginId"));
        wishService.delete(vo);
        return "/user/myPage/wishList";
    }

    /**
     * 체크된 항목 삭제
     * param : productCode의 배열 ( productCodes로 해주세요. )
     * */
    @PostMapping(value = "/logined-user/mywish/checked-delete")
    public ModelAndView checkedDelete (WishVO vo, ModelAndView mv, HttpSession httpSession){

        vo.setUserId((String) httpSession.getAttribute("loginId"));
        mv.setViewName("/user/myPage/wishList");

        log.info("{}",vo.getProductCodes());

        if(vo.getProductCodes() == null) {
            mv.addObject("wishList",wishService.selectList((String) httpSession.getAttribute("loginId")));
            return mv;
        }

        wishService.checkedDelete(vo);

        mv.addObject("wishList",wishService.selectList((String) httpSession.getAttribute("loginId")));
        return mv;
    }

}