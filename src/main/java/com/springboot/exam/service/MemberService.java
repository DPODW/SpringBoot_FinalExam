package com.springboot.exam.service;


import com.springboot.exam.repository.MemberRepository;
import com.springboot.exam.vo.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class MemberService {

    private final MemberRepository memberRepository;


    public void save(Member member){
        memberRepository.save(member);
    }

    public long findNumber(long number){
        return memberRepository.findNumber(number);
    }

    public void update(Member member,Long number){
        memberRepository.updateMember(member,number);
    }

    public List<Member> login(String id, String pwd){
        return memberRepository.findLoginInfo(id,pwd);
    }
}
