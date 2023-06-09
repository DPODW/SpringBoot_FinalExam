package com.springboot.exam.vo;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "springtest")
@Data
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long number;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String id;

    @Column(nullable = false)
    private String pwd;

    @Column(nullable = false)
    private String tell;
}
