package com.firstproject.project.project.main.record;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Entity
@Setter
@Builder
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Table(name = "record")
@Schema(title = "운동기록")
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(title = "기본키")
    private int rindex;

    @Column(nullable = false)
    @Schema(title = "운동한시간(분)")
    private int emin;

    @Column(nullable = false)
    @Schema(title = "운동이름")
    private String ename;

    @Column(nullable = false)
    @Schema(title = "사용자 아이디")
    private String id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Schema(title = "운동한 날짜")
    private LocalDateTime rdatetime;

    @Transient
    @Schema(title = "이번주 기록")
    private Integer weekwork;

    @Transient
    @Schema(title = "지난주 기록")
    private Integer lastwork;

    @Transient
    @Schema(title = "오늘 운동별 운동시간(분)")
    private int dayemin;

    @Transient
    @Schema(title = "오늘 운동별 소모 칼로리")
    private int daycalories;


    @Transient
    @Schema(title = "변경시 변경할 이름")
    private String rename;

    @Transient
    @Schema(title = "변경시 변경할 운동시간(분)")
    private int retime;

    @Transient
    @Schema(title = "삭제시 삭제할 시간")
    private String date;

}
