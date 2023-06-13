package com.springboot.exam.controller;

import com.springboot.exam.repository.SessionConst;
import com.springboot.exam.service.MemberService;
import com.springboot.exam.vo.Member;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@RequiredArgsConstructor
@Controller
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/join")
    public String join(Member member,Model model){
        model.addAttribute("member", member);
        return "member/join";
    }

    @GetMapping("/join1")
    public String joinEdit(HttpServletRequest request,Model model){
        HttpSession session = request.getSession(false);
        HashMap<String,String> sessionData = (HashMap<String, String>) session.getAttribute(SessionConst.LOGIN_MEMBER);
        String sessionDataToString = sessionData.toString().replaceAll("[{}]", "");
        String[] loginInfo = sessionDataToString.split("=");
        String id = loginInfo[0];
        String pwd = loginInfo[1];

        List<Member> members = memberService.login(id, pwd);
        Member member = members.get(0);
        model.addAttribute(member);
        return "member/joinEdit";
    }

    @GetMapping("/login")
    public String login(){
        return "login/login";
    }


    @PostMapping("/join")
    public String memberJoin(@Validated @ModelAttribute Member member, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "member/join";
        }else
        memberService.save(member);
        model.addAttribute(member);
        return "login/loginMember";
    }

    @PostMapping("/join1")
    public String memberUpdate(@Validated @ModelAttribute Member member, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "member/joinEdit";
        }else {
            long number = memberService.findNumber(member.getNumber());
            memberService.update(member,number);
            return "redirect:/";
        }
    }

    @PostMapping("/login")
    public String memberLogin(@RequestParam("id") String id, @RequestParam("pwd") String pwd, Model model, HttpServletRequest request){
        if(!memberService.login(id,pwd).isEmpty()){
            HttpSession session = request.getSession();
            Map<String,String> sessionInfo = new HashMap<>();
            sessionInfo.put(id,pwd);
            session.setAttribute(SessionConst.LOGIN_MEMBER,sessionInfo);

            return "redirect:/1";
        }else
            model.addAttribute("loginFail",true);
            return "login/login";
    }

    @PostMapping("/logout")
    public String memberLogout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
            log.info("로그아웃. 세션 삭제");
        }else
            log.info("세션이 이미 삭제되었거나, 없음");
           return "redirect:/";
    }
}
