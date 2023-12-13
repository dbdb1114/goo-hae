package com.kdt.goohae.controller.admin;


import com.kdt.goohae.domain.admin.ManagerVO;
import com.kdt.goohae.domain.forPaging.PageMaker;
import com.kdt.goohae.domain.forPaging.SearchCri;
import com.kdt.goohae.domain.user.UserVO;
import com.kdt.goohae.service.admin.AdminService;
import com.kdt.goohae.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;


@Slf4j
@Controller
public class AdminController {

    // 필드
    private final AdminService service;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    // 생성자
    public AdminController(AdminService service, PasswordEncoder passwordEncoder, UserService userService) {
        this.service = service;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }


    /**
     * 관리자 메인페이지로 가기 위한 컨트롤러
     *
     * @return 관리자 메인 페이지
     */
    @GetMapping("/admin")
    public String adminMain() {
        return "admin/main";
    }


    /**
     * 관리자 로그인페이지로 가기 위한 컨트롤러
     *
     * @return 관리자 로그인 페이지
     */
    @GetMapping("/admin-loginf")
    public String adminLoginF() {
        return "admin/adminLogin";
    }


    /**
     * 로그인을 위한 컨트롤러
     *
     * @param vo      ManagerVO
     * @param mv      ModelAndView
     * @param request HttpServletRequest
     * @return 성공 시 세션 추가 및 adminMain으로 | 실패 시 메시지와 함께 로그인 화면으로
     */
    @PostMapping("/admin-login")
    public ModelAndView adminLogin(ManagerVO vo, ModelAndView mv, HttpServletRequest request) {
        String pw = vo.getPassword();
        String uri = "admin/adminLogin";
        vo = service.selectOne(vo);

        if (vo != null) {
            if (passwordEncoder.matches(pw, vo.getPassword())) {
                HttpSession session = request.getSession();

                session.setAttribute("adminID", vo.getId());
                session.setAttribute("adminName", vo.getName());

                uri = "redirect:/admin";
            } else {
                mv.addObject("message", "확인 후 다시 시도하세요.");
            }
        } else {
            mv.addObject("message", "확인 후 다시 시도하세요.");
        }

        mv.setViewName(uri);
        return mv;
    }


    /**
     * 로그아웃을 위한 컨트롤러
     *
     * @param request HttpServletRequest
     * @return 세션 무효화 후 로그인 화면으로
     */
    @GetMapping("/admin-logout")
    public String adminLogout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "admin/adminLogin";
    }


    /**
     * 유저 관리 페이지를 위한 컨트롤러
     *
     * @param cri       SearchCri
     * @param pageMaker PageMaker
     * @param model     Model
     * @return 유저 관리 페이지
     */
    @GetMapping("/admin/user-list")
    public String userList(SearchCri cri, PageMaker pageMaker, Model model,
                           @RequestParam(value = "message", required = false) String message) {
        cri.setStartNum();

        if (cri.getKeyword() == null) {
            cri.setKeyword("");
        }

        // 유져 정보 담기
        model.addAttribute("users", userService.selectList(cri));

        // 페이징을 위한 정보 넣기
        model.addAttribute("check", cri.getCheck());
        model.addAttribute("keyword", cri.getKeyword());

        // 페이징 객체 채워 넣기
        pageMaker.setCriteria(cri);
        pageMaker.setTotalDataCount(userService.getTotalData());
        model.addAttribute("pageMaker", pageMaker);

        if (message != null && message.length() > 0) {
            model.addAttribute("message", message);
        }

        return "admin/userList";
    }


    /**
     * 유저 삭제를 위한 컨트롤러
     * @param vo UserVO
     * @param mv ModelAndView
     * @param rttr RedirectAttributes
     * @return 메시지와 함께 리다이렉트
     */
    @GetMapping("/admin/del-user")
    public ModelAndView deleteUser(UserVO vo, ModelAndView mv, RedirectAttributes rttr) {
        String uri = "redirect:/admin/user-list";

        if (userService.delete(vo) > 0) {
            rttr.addAttribute("message", "삭제에 성공했습니다.");
        } else {
            rttr.addAttribute("message", "삭제에 실패했습니다.");
        }

        mv.setViewName(uri);
        return mv;
    }
}
