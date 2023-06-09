package com.springboot.exam.controller;

import com.springboot.exam.service.MemberService;
import com.springboot.exam.vo.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Slf4j
@RequiredArgsConstructor
@Controller
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/join")
    public String join(){
        return "member/join";
    }

    @GetMapping("/join1")
    public String joinEdit(){
        return "member/joinEdit";
    }

    @GetMapping("/login")
    public String login(){
        return "login/login";
    }


    @PostMapping("/join")
    public String memberJoin(@ModelAttribute Member member, Model model){
        memberService.save(member);
        model.addAttribute(member);
        return "login/loginMember";
    }

    @PostMapping("/login")
    public String memberLogin(@RequestParam("id") String id, @RequestParam("pwd") String pwd){
       if(!memberService.login(id,pwd).isEmpty()){
           log.info("{}",memberService.login(id,pwd));
           return "member/joinEdit";
       }else
           return "member/join";
    }



}
