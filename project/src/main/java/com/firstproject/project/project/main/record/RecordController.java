package com.firstproject.project.project.main.record;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("main/record")
@Tag(name = "RecordController", description = "운동기록 조회/추가/삭제 기능")
public class RecordController {

    private final RecordService recordService;

    @Operation(summary = "운동기록 조회")
    @PostMapping("")
    public ResponseEntity<String> Week(@RequestBody RecordDto recordDto) {
        String text = recordService.Week(recordDto.getId());

        return ResponseEntity.status(HttpStatus.OK).body(text);

    }

    @Operation(summary = "운동기록 삭제")
    @DeleteMapping("")
    public String Deletework(@RequestBody RecordDto recordDto) {
        String text = recordService.delete(recordDto);
        return text;
    }

    @Operation(summary = "운동기록 추가")
    @PostMapping("/insert")
    public ResponseEntity<Record> insertwork(@RequestBody RecordDto recordDto) {
        recordDto.setRdatetime(LocalDateTime.now());
        ModelMapper mapper = new ModelMapper();
        Record record = mapper.map(recordDto, Record.class);
        Record dbrecord = recordService.regist(record);
        return ResponseEntity.status(HttpStatus.OK).body(dbrecord);
    }

    @Operation(summary = "운동기록 수정")
    @PutMapping("")
    public String updatework(@RequestBody RecordDto recordDto) {
        // rename이 빈 문자열인 경우 null로 설정
        if ("".equals(recordDto.getRename())) {
            recordDto.setRename(null);
        }
        // retime이 null인 경우 0으로 설정
        if ("".equals(recordDto.getRetime())) {
            recordDto.setRetime(0);
        }

        ModelMapper mapper = new ModelMapper();
        Record record = mapper.map(recordDto, Record.class);

        String text = recordService.update(record);
        return text;
    }

}