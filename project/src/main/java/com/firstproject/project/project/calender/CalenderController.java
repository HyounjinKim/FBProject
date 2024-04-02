package com.firstproject.project.project.calender;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/calender")
@RequiredArgsConstructor
@Tag(name = "CalenderController",description = "하루,한달 운동/음식 칼로리 조회 기능")
public class CalenderController {

    private final CalenderService calenderService;

    @Operation(summary = "하루 운동,음식 칼로리 조회")
    @PostMapping("/day")
    public ResponseEntity<String> calenderday(@RequestBody CalenderUserDate calenderUserDate) {
        String result = calenderService.calday(calenderUserDate.getId(), calenderUserDate.getDatetime());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Operation(summary = "한달 운동,음식 칼로리 조회")
    @PostMapping("/month")
    public ResponseEntity<String> calendermonth(@RequestBody CalenderUserDate calenderUserDate) {
        String result = calenderService.calmonth(calenderUserDate.getId());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
