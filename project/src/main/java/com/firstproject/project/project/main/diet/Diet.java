package com.firstproject.project.project.main.diet;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "diet")
@Schema(name = "먹은음식 정보")
public class Diet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(title = "기본키")
    private int didx;

    @Column(nullable = false)
    @Schema(title = "사용자 아이디")
    private String id;

    @Column(nullable = false)
    @Schema(title = "음식이름")
    private String dname;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Schema(title = "음식먹은 날짜")
    private LocalDateTime ddatetime;

    @Column(nullable = false)
    @Schema(title = "음식 칼로리")
    private float dcalories;


    @Transient
    @Schema(title = "이번주 총 섭취 칼로리")
    private Integer weekcalories;


    @Transient
    @Schema(title = "지난주 총 섭취 칼로리")
    private Integer lastcalories;


    @Transient
    @Schema(title = "오늘 총 섭취 칼로리")
    private float daycalories;

    @Transient
    @Schema(title = "삭제시 삭제할 시간")
    private String date;


    @Transient
    @Schema(title = "변경시 변경할 이름")
    private String rename;

    @Transient
    @Schema(title = "변경시 변경할 칼로리")
    private float recalories;

}
