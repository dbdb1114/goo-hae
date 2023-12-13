package com.kdt.goohae.config;

import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserCertificationInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler ) throws  Exception {
        if (ObjectUtils.isEmpty(httpServletRequest.getSession().getAttribute("loginId"))){
            httpServletResponse.sendRedirect("/user/login");
            return false;
        } else {
            return true;
        }
    }

}
