package com.kdt.goohae.controller.admin;


import com.kdt.goohae.domain.admin.PostBoardVO;
import com.kdt.goohae.domain.forPaging.PageMaker;
import com.kdt.goohae.domain.forPaging.SearchCri;
import com.kdt.goohae.service.admin.PostBoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PostBoardController {
    
    // 필드
    private final PostBoardService service;

    // 생성자
    public PostBoardController(PostBoardService service) {
        this.service = service;
    }


    /**
     * 게시글 등록 페이지를 위한 컨트롤러
     * @return 게시글 등록 페이지
     */
    @GetMapping("/admin/reg-post")
    public String regPostF() {
        return "admin/regPostBoard";
    }


    /**
     * 게시글 등록을 위한 컨트롤러
     * @param vo PostBoardVO
     * @param mv ModelAndView
     * @param request HttpServletRequest
     * @return 메시지와 등록 페이지로
     */
    @PostMapping("/admin/reg-post")
    public ModelAndView regPost(PostBoardVO vo, ModelAndView mv, HttpServletRequest request) {
        vo.setManagerId((String)request.getSession().getAttribute("adminID"));
        String uri = "admin/regPostBoard";

        if (service.insert(vo) > 0) {
            mv.addObject("message", "등록에 성공하였습니다.");
        } else {
            mv.addObject("message", "등록에 실패하였습니다.");
        }

        mv.setViewName(uri);
        return mv;
    }


    /**
     * 게시글 데이터를 가져오기 위한 컨트롤러
     * @param pageMaker PageMaker
     * @param cri SearchCri
     * @param model Model
     * @return 게시글 데이터 List, 페이징을 위한 객체, 게시글 관리 페이지
     */
    @GetMapping("/admin/post-list")
    public String postList(PageMaker pageMaker, SearchCri cri, Model model,
                           @RequestParam(value = "message", required = false) String message) {
        cri.setStartNum();

        // 게시글 데이터 넣기
        model.addAttribute("board", service.selectList(cri));

        // 페이징을 위한 정보 넣기
        model.addAttribute("check", cri.getCheck());

        // 페이징을 위한 객체 완성 후 model에 넣기
        pageMaker.setCriteria(cri);
        pageMaker.setTotalDataCount(service.getTotalData());
        model.addAttribute("pageMaker", pageMaker);

        if (message != null && message.length() > 0) {
            model.addAttribute("message", message);
        }

        return "admin/postBoard";
    }


    /**
     * 게시글 삭제를 위한 컨트롤러
     * @param vo PostBoardVO
     * @param mv ModelAndView
     * @param rttr RedirectAttributes
     * @return 메시지와 게시글 관리 페이지로
     */
    @GetMapping("/admin/del-post")
    public ModelAndView delPostBoard(PostBoardVO vo, ModelAndView mv, RedirectAttributes rttr) {
        String uri = "redirect:/admin/post-list";

        if (service.delete(vo) > 0) {
            rttr.addAttribute("message", "삭제에 성공했습니다.");
        } else {
            rttr.addAttribute("message", "삭제에 실패했습니다.");
        }

        mv.setViewName(uri);
        return mv;
    }


    /**
     * 게시글 디테일 페이지를 위한 컨트롤러
     * @param vo PostBoardVO
     * @param model Model
     * @return 게시글 디테일 페이지
     */
    @GetMapping("/admin/detail-post")
    public String postDetail(PostBoardVO vo, Model model) {
        model.addAttribute("board", service.selectOne(vo));
        return "admin/postDetail";
    }


    /**
     * 게시글 수정을 위한 컨트롤러
     * @param vo PostBoardVO
     * @param mv ModelAndView
     * @param rttr RedirectAttributes
     * @return 메시지와 함께 게시글 관리 페이지로
     */
    @PostMapping("/admin/update-post")
    public ModelAndView updatePostBoard(PostBoardVO vo, ModelAndView mv, RedirectAttributes rttr) {
        String uri = "redirect:/admin/post-list";

        if (service.update(vo) > 0) {
            rttr.addAttribute("message", "수정이 완료되었습니다.");
        } else {
            rttr.addAttribute("message", "수정에 실패하였습니다.");
        }

        mv.setViewName(uri);
        return mv;
    }
}
