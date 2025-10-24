package com.ui.demo.config;

import com.ui.demo.entity.User;
import com.ui.demo.entity.UserSession;
import com.ui.demo.repository.UserSessionRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Component
public class SessionInterceptor implements HandlerInterceptor {
@Autowired
private UserSessionRepository sessionRepository;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(request.getRequestURI().equals("/ui/user/login") || request.getRequestURI().equals("/ui/user/signup")){
            return true;

        }else if(request.getRequestURI().equals("/ui/user/all-data")) {
            String authHeader = request.getHeader("Authorization");

            if (authHeader == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }

            String token = authHeader;

            Optional<UserSession> sessionOpt = sessionRepository.findByToken(token);

            if (sessionOpt.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }

            UserSession session = sessionOpt.get();

            if (session.getExpiresAt().isBefore(LocalDateTime.now())) {
                sessionRepository.delete(session);
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }


            sessionRepository.save(session);

            return true;

        }


        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
