package com.firstproject.project.project.main.record;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@Schema(title = "운동기록Dto", description = "Record DB 유효성 클래스")
public class RecordDto {
    @Schema(title = "기본키")
    private int rindex;

    @Schema(title = "운동한시간(분)")
    private int emin;

    @Schema(title = "운동이름")
    private String ename;

    @Schema(title = "사용자 아이디")
    private String id;

    @Schema(title = "운동한 날짜")
    private LocalDateTime rdatetime;

    @Schema(title = "변경시 변경할 이름")
    private String rename;

    @Schema(title = "변경시 변경할 운동시간(분)")
    private int retime;

    @Schema(title = "삭제시 삭제할 시간")
    private String date;

}
