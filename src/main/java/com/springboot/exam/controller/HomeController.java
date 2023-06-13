package com.springboot.exam.controller;


import jakarta.servlet.http.HttpServletRequest;
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
    public String home(HttpServletRequest request){
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
