package com.springboot.exam.vo;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name = "board")
@Data
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long number;

    @Column(nullable = false)
    private String id;

    @Column(nullable = false)
    @Length(min=3,max = 20)
    private String title;

    @Column(nullable = false)
    @Length(min=5,max = 100)
    private String content;
}
