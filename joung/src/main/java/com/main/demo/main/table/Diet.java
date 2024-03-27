package com.main.demo.main.table;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Diet")
public class Diet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int didx;
    private String id;
    private String dname;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime ddatetime;

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
