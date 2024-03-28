package com.firstproject.project.project.main.diet;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Diet")
@Schema(name = "먹은음식 정보")
public class Diet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int didx;

    @Column(nullable = false)
    private String id;
    @Column(nullable = false)
    private String dname;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime ddatetime;

    @Column(nullable = false)
    private float dcalories;

    //이번주 총 섭취 칼로리
    @Transient
    private Integer weekcalories;

    //지난주 총 섭취 칼로리
    @Transient
    private Integer lastcalories;

    //오늘 총 섭취 칼로리
    @Transient
    private int daycalories;

}
