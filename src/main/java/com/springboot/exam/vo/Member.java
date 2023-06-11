package com.springboot.exam.vo;

import jakarta.persistence.*;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;


@Entity
@Component
@Table(name = "springtest")

@Data
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long number;

    @Column(nullable = false)
    @Length(min=2,max = 10)
    private String name;

    @Column(nullable = false)
    @Length(min = 3,max = 12)
    private String id;

    @Column(nullable = false)
    @Length(min = 3,max = 12)
    private String pwd;

    @Column(nullable = false)
    @Length(min = 11,max = 11)
    private String tell;
}
