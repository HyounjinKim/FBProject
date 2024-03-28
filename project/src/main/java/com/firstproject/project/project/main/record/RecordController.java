package com.firstproject.project.project.main.record;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("main/record")
@Tag(name = "RecordController",description = "운동기록 조회/추가/삭제 기능")
public class RecordController {

    private final RecordService recordService;

    @Operation(summary = "운동기록 조회")
    @PostMapping("")
    public ResponseEntity<List<Record>> week(@RequestBody RecordDto recordDto) {
        List<Record> list = recordService.Week(recordDto.getId());

        return ResponseEntity.status(HttpStatus.OK).body(list);

    }

    @Operation(summary = "운동기록 삭제")
    @DeleteMapping("")
    public String Deletework(@RequestBody Map<String, String> requestBody) {

        RecordDto recordDto = new RecordDto();
        String id = requestBody.get("id");
        String ename = requestBody.get("ename");
        String date = requestBody.get("date");
        recordDto.setId(id);
        recordDto.setEname(ename);
        String text = recordService.delete(recordDto, date);

        return text;

    }

    @Operation(summary = "운동기록 추가")
    @PostMapping("/insert")
    public ResponseEntity<Record> insertwork(@RequestBody RecordDto recordDto) {
        recordDto.setRdatetime(LocalDateTime.now());
        Record record =  recordService.regist(recordDto);

        return ResponseEntity.status(HttpStatus.OK).body(record);
    }

    @Operation(summary = "운동기록 수정")
    @PutMapping("")
    public void updatework(@RequestBody Map<String, String> requestBody) {
        RecordDto recordDto = new RecordDto();
        String rename = requestBody.get("rename");
        String retime = requestBody.get("retime");

        recordService.update(recordDto, rename, retime);
    }
}