package com.main.demo.main.table;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Entity
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Record")
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rindex;

    private int emin;

    private String ename;

    private String id;


    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime rdatetime;

    //이번주 기록
    @Transient
    private Integer weekwork;

    //지난주 기록
    @Transient
    private Integer lastwork;

    //오늘의 해당 운동 시간
    @Transient
    private int dayemin;

    //오늘의 해당 운동의 총 칼로리
    @Transient
    private int daycalories;


}
