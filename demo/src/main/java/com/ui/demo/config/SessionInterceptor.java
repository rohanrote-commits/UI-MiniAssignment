package com.ui.demo.config;

import com.ui.demo.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
@Slf4j
@Component
public class SessionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(request.getRequestURI().equals("/ui/user/login") || request.getRequestURI().equals("/ui/user/signup")){
            return true;

        }else if(request.getRequestURI().equals("/ui/user/all-data")) {
            HttpSession session = request.getSession(false);
            User user = (session != null) ? (User) session.getAttribute("user") : null;

            if (user != null) {
                log.info("Session is active");
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                log.info("Session is not active");
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No active session");
            }
            return false; // stop further processing
        }


        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
