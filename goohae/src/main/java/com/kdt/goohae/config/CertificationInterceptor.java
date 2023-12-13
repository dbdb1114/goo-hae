package com.kdt.goohae.config;


import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CertificationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (ObjectUtils.isEmpty(request.getSession().getAttribute("adminID"))) {
            response.sendRedirect("/admin-loginf");
            return false;
        } else {
            return true;
        }
    }

}
