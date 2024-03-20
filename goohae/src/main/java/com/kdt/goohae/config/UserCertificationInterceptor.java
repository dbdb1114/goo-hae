package com.kdt.goohae.config;

import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RequiredArgsConstructor
public class UserCertificationInterceptor implements HandlerInterceptor {

    private final RedisTemplate redisTemplate;

    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler ) throws  Exception {

        HttpSession session = httpServletRequest.getSession();
        String loginId = (String) session.getAttribute("loginId");
        String sessionId = session.getId();

        ValueOperations valueOperations = redisTemplate.opsForValue();

        String redisSessionId = (String) valueOperations.get(loginId);

        // 로그인을 하지 않았거나 || 레디스에 저장된 세션아이디와 현재 요청중인 세션 아이디가 다르다면
        if (ObjectUtils.isEmpty(redisSessionId) || !sessionId.equals(redisSessionId)){
            httpServletResponse.sendRedirect("/user/login");
            return false;
        } else {
            return true;
        }
    }

}
