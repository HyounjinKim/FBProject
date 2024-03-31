package com.firstproject.project.project.calender;

import io.swagger.v3.oas.annotations.Operation;
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
public class CalenderController {

    private final CalenderService calenderService;

    @Operation(summary = "캘린더 눌렀을 때 하루치 결과값")
    @PostMapping("/day")
    public ResponseEntity<String> calenderday(@RequestBody CalenderUserDate calenderUserDate) {
        String result = calenderService.calday(calenderUserDate.getId(), calenderUserDate.getDatetime());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Operation(summary = "한달치 결과 값 한번에 보기")
    @PostMapping("/month")
    public ResponseEntity<String> calendermonth(@RequestBody CalenderUserDate calenderUserDate) {
        String result = calenderService.calmonth(calenderUserDate.getId());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
