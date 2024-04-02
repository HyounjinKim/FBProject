package com.firstproject.project.project.main.diet;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@Schema(name = "먹은음식Dto", description = "Diet DB 유효성 클래스")
public class DietDto {

    @Schema(title = "기본키")
    private int didx;

    @Schema(title = "사용자 아이디")
    private String id;

    @Schema(title = "음식이름")
    private String dname;
    @Schema(title = "음식먹은 날짜")
    private LocalDateTime ddatetime;

    @Schema(title = "음식 칼로리")
    private float dcalories;

    @Schema(title = "이번주 총 섭취 칼로리")
    private Integer weekcalories;

    @Schema(title = "지난주 총 섭취 칼로리")
    private Integer lastcalories;

    @Schema(title = "오늘 총 섭취 칼로리")
    private int daycalories;

    @Schema(title = "삭제시 삭제할 시간")
    private String date;

    @Schema(title = "변경시 변경할 이름")
    private String rename;

    @Schema(title = "변경시 변경할 칼로리")
    private int recalories;

}
