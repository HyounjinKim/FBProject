package com.firstproject.project.project.main.diet;

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
@RequestMapping("main/eat")
@Tag(name = "DietController",description = "음식기록 조회/추가/삭제 기능")
public class DietController {

    private final DietService dietService;

    @Operation(summary = "음식기록 조회")
    @PostMapping("")
    public ResponseEntity<List<Diet>> week(@RequestBody DietDto dietDto) {
        List<Diet> list = dietService.Week(dietDto.getId());

        return ResponseEntity.status(HttpStatus.OK).body(list);

    }


    @Operation(summary = "음식기록 삭제")
    @DeleteMapping("")
    public String Deleteat(@RequestBody Map<String, String> requestBody) {
        DietDto dietDto = new DietDto();
        String id = requestBody.get("id");
        String dname = requestBody.get("dname");

        dietDto.setId(id);
        dietDto.setDname(dname);
        String date = requestBody.get("date");

        String text =   dietService.delete(dietDto, date);

        return text;
    }

    @Operation(summary = "음식기록 추가")
    @PostMapping("/insert")
    public ResponseEntity<Diet> inserteat(@RequestBody Diet dite){
        dite.setDdatetime(LocalDateTime.now());
        dietService.regist(dite);
        return ResponseEntity.status(HttpStatus.OK).body(dite);
    }

}
