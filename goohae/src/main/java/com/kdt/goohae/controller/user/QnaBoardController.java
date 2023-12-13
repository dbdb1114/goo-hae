package com.kdt.goohae.controller.user;

import com.kdt.goohae.domain.forPaging.Criteria;
import com.kdt.goohae.domain.forPaging.PageMaker;
import com.kdt.goohae.domain.forPaging.SearchCri;
import com.kdt.goohae.domain.user.QnaBoardVO;
import com.kdt.goohae.service.user.QnaBoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Slf4j
@Controller
public class QnaBoardController {

    QnaBoardService qnaBoardService;

    public QnaBoardController(QnaBoardService qnaBoardService) { this.qnaBoardService = qnaBoardService; }

    /**
     * Qna 리스트
     * param: paing 객체 pageMaker
     * */
    @GetMapping(value = "qna-board/list")
    public String selectList(Model model, PageMaker pageMaker, Criteria cri){

        cri.setStartNum();

        model.addAttribute("qnaList",qnaBoardService.selectlList(cri));

        pageMaker.setCriteria(cri);
        pageMaker.setTotalDataCount(qnaBoardService.getTotalData());

        model.addAttribute("pageMaker",pageMaker);

        log.info("{}",model.getAttribute("qnaList"));

        return "customer/customerFaq";
    }

    /**
     * 내 작성글 리스트
     * 로그인 했다면, model에 "userQnaList"에 담아서 리턴
     * */
    @PostMapping(value = "logined-user/qna-board/user-list")
    public Model userList (Model model, HttpSession httpSession ){
        String loginId = (String) httpSession.getAttribute("loginId");
        model.addAttribute("userQnaList", qnaBoardService.userList(loginId));
        return model;
    }

    /**
     * 글 상세
     * 해당 글의 비밀번호가 있는지 없는지 확인, 있다면 넘어온 값이 맞는지 확인
     * 전부 맞다면, "qnaDetail"에 담아서 리턴
     * param : boardSeq
     * param : boardPassword ( not required )
     * */
    @GetMapping(value = "qna-board/detail")
    public ModelAndView selectOne(QnaBoardVO vo, ModelAndView mv){
        String pw = qnaBoardService.selectOne(vo).getBoardPassword();
        if (pw == null || pw.equals(vo.getBoardPassword())){
            mv.addObject("qnaDetail",qnaBoardService.selectOne(vo));
            mv.setViewName("customer/qnaDetail");
        }
        return mv;
    }

    /**
     * qna 글 추가
     * 로그인한 상태인지 확인
     * 글 추가 실행
     * param : userId
     * param : title
     * param : content
     * param : boardPassword
     * 성공 : success ( session )
     * 실패 : failed ( session )
     * */
    @PostMapping(value = "logined-user/qna-board/insert")
    public String insert (QnaBoardVO vo, HttpSession httpSession){
        if ( httpSession.getAttribute("loginId") == null ){
            httpSession.setAttribute("message", "login-required");
        } else {
            vo.setUserId((String) httpSession.getAttribute("loginId"));
            if ( qnaBoardService.insert(vo)>0 ){
                httpSession.setAttribute("message", "success");
            }
            httpSession.setAttribute("message","failed");
        }
        return "/customer/customerMain";
    }

    /**
     * qna글 삭제
     * qna글의 pw가 있는지, 있다면 입력받은 값이 맞는지 확인
     * qna글의 id와 session의 loginId가 같은지 확인
     * loginID가 있다면 삭제 실행 후 리턴 ( 실패해도 admin/main으로 이동 )
     * 이후 삭제
     * param : boardSeq
     * param : userId ( 삭제하려는 글의 작성 id )
     * param : baordPassword ( not required )
     * 성공 : success ( session )
     * 실패 :
     *  다른 id : wrongLoginId
     *  다른 pw : wrongPassword
     * */
    @PostMapping(value = "logined-user/qna-board/delete")
    public String delete ( QnaBoardVO vo, HttpSession httpSession ){

        QnaBoardVO dbvo = qnaBoardService.selectOne(vo);

        if (httpSession.getAttribute("adminID") != null) {
            if(qnaBoardService.delete(vo) > 0) httpSession.setAttribute("message", "success");
            return "admin/main";
        }

        if ("null".equals(dbvo.getBoardPassword()) || dbvo.getBoardPassword().equals(vo.getBoardPassword())){
            if( dbvo.getUserId().equals((String)httpSession.getAttribute("loginId")) && qnaBoardService.delete(vo)>0 ){
                httpSession.setAttribute("message", "success");
            } else {
                httpSession.setAttribute("message", "wrongLoginId");
            }
        } else {
            httpSession.setAttribute("message", "wrongPassword");
        }

        return "/customer/customerMain";
    }

}
