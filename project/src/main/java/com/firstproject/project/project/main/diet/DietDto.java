package com.firstproject.project.project.main.diet;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;



@Getter
@Setter
@ToString
@Schema(name = "먹은음식Dto",description = "Diet DB 유효성 클래스")
public class DietDto {

    @Schema(title = "기본키")
    private int didx;
    @NotBlank
    @Schema(title = "사용자 아이디")
    private String id;
    @NotBlank
    @Schema(title = "음식이름")
    private String dname;
    @Schema(title = "음식먹은 날짜")
    private LocalDateTime ddatetime;
    @NotBlank
    @Schema(title = "음식 칼로리")
    private float dcalories;



    public Diet of(DietDto dietDTO) {
        Diet diet = new Diet();
        diet.setDidx(dietDTO.getDidx());
        diet.setId(dietDTO.getId());
        diet.setDname(dietDTO.getDname());
        diet.setDdatetime(dietDTO.getDdatetime());
        diet.setDcalories(dietDTO.getDcalories());
        return diet;
    }
}
