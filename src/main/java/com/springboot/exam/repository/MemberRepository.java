package com.springboot.exam.repository;

import com.springboot.exam.vo.Member;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
    @Query("SELECT m FROM Member m WHERE m.id = ?1 AND m.pwd = ?2")
    List<Member> findLoginInfo(String id, String pwd);


    @Query("SELECT m.number FROM Member m WHERE m.number = ?1")
    Long findNumber(long number);

    @Transactional
    @Modifying
    @Query("update Member m set m.name = :#{#member.name}, m.pwd = :#{#member.pwd}, m.tell = :#{#member.tell} where m.number = :number")
    void updateMember(Member member,Long number);
}
