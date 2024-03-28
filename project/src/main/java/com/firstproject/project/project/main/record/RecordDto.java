package com.firstproject.project.project.main.record;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@Schema(title = "운동기록Dto",description = "Record DB 유효성 클래스")
public class RecordDto {
    @Schema(title = "기본키")
    private int rindex;

    @NotBlank
    @Schema(title = "운동한시간(분)")
    private int emin;
    @NotBlank
    @Schema(title = "운동이름")
    private String ename;
    @NotBlank
    @Schema(title = "사용자 아이디")
    private String id;
    @Schema(title = "운동한 날짜")
    private LocalDateTime rdatetime;
    public static Record of(RecordDto recordDTo) {
        Record record = new Record();
        record.setEname(recordDTo.getEname());
        record.setId(recordDTo.getId());
        record.setEmin(recordDTo.getEmin());
        record.setRdatetime(LocalDate.from(recordDTo.getRdatetime()).atStartOfDay());
        return record;
    }
}
