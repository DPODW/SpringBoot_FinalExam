package com.springboot.exam.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class HomeController {

    /**
     * 메인화면에선 세션이 존재해선 안됌
     * */
    @GetMapping("/")
    public String home(HttpServletRequest request, HttpServletResponse response){
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setHeader("Expires", "0"); // Proxies.
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }
        return "index";
    }

    @GetMapping("/1")
    public String home1(){
        return "index1";
    }
}
