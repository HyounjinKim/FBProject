package com.firstproject.project.project.main.diet;


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
@RequestMapping("main/eat")
@Tag(name = "DietController", description = "음식기록 조회/추가/삭제 기능")
public class DietController {

    private final DietService dietService;

    @Operation(summary = "음식기록 조회")
    @PostMapping("")
    public ResponseEntity<String> week(@RequestBody DietDto dietDto) {
        String text = dietService.Week(dietDto.getId());

        return ResponseEntity.status(HttpStatus.OK).body(text);

    }


    @Operation(summary = "음식기록 삭제")
    @DeleteMapping("")
    public String Deleteat(@RequestBody DietDto dietDto) {
        String text = dietService.delete(dietDto);
        return text;
    }

    @Operation(summary = "음식기록 추가")
    @PostMapping("/insert")
    public ResponseEntity<Diet> inserteat(@RequestBody DietDto dietDto) {
        dietDto.setDdatetime(LocalDateTime.now());
        ModelMapper mapper = new ModelMapper();
        Diet diet = mapper.map(dietDto, Diet.class);
        Diet dbdiet = dietService.regist(diet);
        return ResponseEntity.status(HttpStatus.OK).body(dbdiet);
    }


    @Operation(summary = "음식기록 수정")
    @PutMapping("")
    public String updatediet(@RequestBody DietDto dietDto) {
        // rename이 빈 문자열인 경우 null로 설정
        if ("".equals(dietDto.getRename())) {
            dietDto.setRename(null);
        }
        // Recalories이 null인 경우 0으로 설정
        if ("".equals(dietDto.getRecalories())) {
            dietDto.setRecalories(0);
        }

        ModelMapper mapper = new ModelMapper();
        Diet diet = mapper.map(dietDto, Diet.class);

        // null 체크 후 update 메서드 호출
        String text = dietService.update(diet);
        return text;

    }


}
