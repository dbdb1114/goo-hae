package com.kdt.goohae.controller.user;

import com.kdt.goohae.domain.user.UserVO;
import com.kdt.goohae.service.user.CartService;
import com.kdt.goohae.service.user.QnaBoardService;
import com.kdt.goohae.service.user.ReviewService;
import com.kdt.goohae.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final ReviewService reviewService;
    private final CartService cartService;
    private final QnaBoardService qnaBoardService;
    private final RedisTemplate redisTemplate;

    public UserController(UserService userService, PasswordEncoder passwordEncoder, ReviewService reviewService, CartService cartService, QnaBoardService qnaBoardService,
                          RedisTemplate redisTemplate) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.reviewService = reviewService;
        this.cartService = cartService;
        this.qnaBoardService = qnaBoardService;
        this.redisTemplate = redisTemplate;
    }

    @GetMapping(value = "user/join")
    public String joinF(UserVO vo) { return "user/singlePage/signUp";  }

    @ResponseBody
    @PostMapping(value = "user/join/id-duple-check")
    public String idCheck(UserVO vo, ModelAndView mv){
        String message;
        if(userService.idCheck(vo)>0){
            log.info("dupel");
            message = "duplicatedID";
        }else {
            log.info("Nodupel");
            message= "NotDuplicatedID";
        }
        return message;
    }

    /**
     * 해당 id가 있는지 확인
     * 있으면 세션에 "ID중복" 메세지 전달
     * 없으면 insert 메소드 실행 후 success 인지 fail인지 전달.
     * 실패시 : join화면으로
     * param : id
     * param : password
     * param : name
     * param : postNumber
     * param : address
     * param : phoneNumber
     * */
    @PostMapping(value = "user/join")
    public ModelAndView join ( UserVO vo, ModelAndView mv) {
        mv.setViewName("/user/singlePage/signUp");
        if(userService.selectOne(vo) == null){
            vo.setPassword(passwordEncoder.encode(vo.getPassword()));
            if ( userService.insert(vo)>0 ) {
                mv.addObject("message", "success");
                mv.addObject("message", "success");
                mv.setViewName("user/singlePage/login");
            } else {
                mv.addObject("message","fail");
            }
        } else {
            mv.addObject("message","duplicatedID");
        }
        return mv;
    }


    /**
     * 로그인 처리
     * 1. 로그인 요청한 아이디로 DB에서 해당 계정 골라오기
     *      1-1. 해당 계정 없으면 --> id오류
     * 2. 패스워드 비교
     *      2-1. 같으면 로그인 ( session에 id와 name 전달 )
     *      2-2. 다르면 Pw오류
     * Param : id
     * param : password
     * */
    @PostMapping (value = "user/login")
    public ModelAndView login(UserVO vo, ModelAndView mv, HttpSession httpSession) {
        UserVO dbVO = userService.selectOne(vo);
        if(dbVO != null){
            if(passwordEncoder.matches(vo.getPassword(), dbVO.getPassword())){


                httpSession.setAttribute("loginId",vo.getId());
                httpSession.setAttribute("name",dbVO.getName());
                httpSession.setAttribute("success", "true");
                mv.setViewName("redirect:/");
            }else{
                mv.addObject("message", "PW오류");
                mv.setViewName("/user/singlePage/login");
            }
        }else {
            mv.addObject("message", "ID오류");
            mv.setViewName("/user/singlePage/login");
        }
        return mv;
    }

    /**
     * User Id 찾기
     * 회원가입 핸드폰 번호, 이름 확인 후 같으면
     * ==> model에 id라고 담아서 전달.
     * param = name ( 이름 )
     * param = phoneNumber ( 핸드폰 번호 )
     * */
    @PostMapping ( value = "user/findId")
    public String findId(UserVO vo, Model model ,HttpSession httpSession) {
        String id = userService.findId(vo);
        if ( id != null){
            model.addAttribute("findId",id);
            return "user/singlePage/login";
        } else {
            httpSession.setAttribute("message", "잘못된 정보입니다.");
            return "user/singlePage/findId";
        }
    }

    /**
     *  UserPW 수정
     *  입력값이랑 이름 , 전화번호 같으면
     *  pw수정 페이지 전달
     *  ( 수정하는 페이지 만들어야함. )
     *  param : id
     *  param : name
     *  param : phoneNumber
     **/
    @PostMapping(value = "user/findPw")
    public ModelAndView findPw(UserVO vo, ModelAndView mv){
        UserVO dbVO = userService.selectOne(vo);
        log.info(String.valueOf(dbVO.getName().equals(vo.getName())));
        log.info(String.valueOf(dbVO.getPhoneNumber().equals(vo.getPhoneNumber())));
        if ( dbVO != null &&
                dbVO.getName().equals(vo.getName())&&
                dbVO.getPhoneNumber().equals(vo.getPhoneNumber())){
            mv.setViewName("user/singlePage/updatePw");
            mv.addObject("id",vo.getId());
            return mv;
        } else {
            mv.setViewName("user/singlePage/findPw");
            return mv;
        }
    }

    @PostMapping(value = "user/updatePw")
    public String chagePw(UserVO vo, HttpSession httpSession,String id) {
        vo.setPassword(passwordEncoder.encode(vo.getPassword()));
        if (userService.changePassword(vo)>0) {
            httpSession.setAttribute("message", "success");
            log.info("succcess");
            return "user/singlePage/login";
        } else {
            httpSession.setAttribute("message","fail");
            return "user/singlePage/findPw";
        }
    }

    /**
     * 로그인 폼
     * */
    @GetMapping (value = "user/login")
    public String loginF() { return "user/singlePage/login"; }

    /**
     * 로그아웃 처리
     * */
    @GetMapping (value = "logined-user/logout")
    public String logout(HttpSession httpSession){
        httpSession.setAttribute("loginId",null);
        return "/index";
    }

    /**
     * 아이디 찾기 폼
     * */
    @GetMapping (value = "user/findid")
    public String findId () {return "user/singlePage/findId";}

    /**
     * 비밀번호 찾기 폼
     * */
    @GetMapping (value = "user/findpw")
    public String findPw () { return "user/singlePage/findPw"; }

    /**
     * 마이페이지
     * */
    @GetMapping (value = "logined-user/mypage")
    public String myPage(){ return "user/myPage/myPage"; }

    /**
     * myPage에 myPost로 이동
     * */
    @GetMapping (value = "logined-user/mypost")
    public String myReview(Model model,HttpSession httpSession){
        model.addAttribute("reviewList",reviewService.getUserReview((String)httpSession.getAttribute("loginId")));

        log.info("{}",reviewService.getUserReview((String)httpSession.getAttribute("loginId")));
        return "user/myPage/myPost";
    }

    @GetMapping (value = "logined-user/myinfo")
    public String myInfo ( Model model, HttpSession httpSession,UserVO vo){
        vo.setId((String) httpSession.getAttribute("loginId"));
        model.addAttribute("user", userService.selectOne(vo));
        return "user/myPage/memberInfo";
    }

    /**
     * 회원정보 수정 섬밋
     * param : password
     * param : name
     * param : postNumber
     * param : address
     * param : phoneNumber
     * */
    @PostMapping (value = "logined-user/myinfo/update")
    public String update (  HttpSession httpSession, UserVO vo ) {
        vo.setId((String) httpSession.getAttribute("loginId"));
        vo.setPassword(passwordEncoder.encode(vo.getPassword()));
        if(userService.update(vo)>0){
            httpSession.setAttribute("message", "success");
            return "/user/myPage/myPage";
        } else {
            httpSession.setAttribute("message", "fail");
            return "/user/myPage/memberInfo";
        }
    }

}
