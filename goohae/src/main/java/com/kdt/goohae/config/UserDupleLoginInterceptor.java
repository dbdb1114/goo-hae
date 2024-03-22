package com.kdt.goohae.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RequiredArgsConstructor
public class UserDupleLoginInterceptor implements HandlerInterceptor {


    private final RedisTemplate redisTemplate;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                            ModelAndView modelAndView) throws Exception {

        String loginId = request.getParameter("id");
        HttpSession session = request.getSession();

        if(request.getMethod().toLowerCase().equals("post")
                && loginId.equals(session.getAttribute("loginId"))){
            ValueOperations valueOperations = redisTemplate.opsForValue();

            if(redisTemplate.hasKey(loginId)){
                log.info(loginId + " : 중복로그인 입니다. 기존 로그인은 로그아웃 됩니다.");
                request.getSession().setAttribute("message", "중복로그인 입니다. 기존 로그인은 로그아웃 됩니다.");
            }

            valueOperations.set(loginId, session.getId());
        }

        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
}
