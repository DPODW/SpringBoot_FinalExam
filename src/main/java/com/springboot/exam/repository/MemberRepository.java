package com.springboot.exam.repository;

import com.springboot.exam.vo.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {

    @Query("SELECT m FROM Member m WHERE m.id = ?1 AND m.pwd = ?2")
    List<Member> findLoginInfo(String id ,String pwd);
}
