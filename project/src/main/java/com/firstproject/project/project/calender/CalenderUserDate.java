package com.firstproject.project.project.calender;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(name = "캘린더 조회 정보")
public class CalenderUserDate {

    @Schema(title = "사용자 아이디")
    private String id;
    @Schema(title = "조회할 날짜")
    private String datetime;

}
