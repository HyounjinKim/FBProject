package com.main.demo.main.work;


import com.main.demo.main.table.Record;
import com.main.demo.main.table.RecordDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("main/work")
public class WorkController {

    private final WorkService workService;


    @PostMapping("")
    public ResponseEntity<List<Record>> week(@RequestBody RecordDTO recordDTO) {
        List<Record> list = workService.Week(recordDTO.getId());

        return ResponseEntity.status(HttpStatus.OK).body(list);

    }

    @DeleteMapping("")
    public String Deletework(@RequestBody Map<String, String> requestBody) {

        RecordDTO recordDTO = new RecordDTO();
        String id = requestBody.get("id");
        String ename = requestBody.get("ename");
        String date = requestBody.get("date");
        recordDTO.setId(id);
        recordDTO.setEname(ename);
       String text = workService.delete(recordDTO, date);

        return text;

    }

    @PostMapping("/insert")
    public ResponseEntity<Record> insertwork(@RequestBody Record record) {
        record.setRdatetime(LocalDateTime.now());
        workService.regist(record);
        System.out.println(record.getRdatetime());
        return ResponseEntity.status(HttpStatus.OK).body(record);
    }

    @PutMapping("")
    public void updatework(@RequestBody Map<String, String> requestBody) {
        RecordDTO recordDTO = new RecordDTO();
        String rename = requestBody.get("rename");
        String retime = requestBody.get("retime");

        workService.update(recordDTO, rename, retime);
    }
}