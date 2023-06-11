package com.springboot.exam.interceptor;

import com.springboot.exam.repository.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class SessionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        if(session==null || session.getAttribute(SessionConst.LOGIN_MEMBER)==null){
            log.info("세션 없음. 미인증 사용자 -> 접근 불가");
            response.sendRedirect("redirect:/");
            return false;
        }
        return true;
    }
}
