package com.firstproject.project.project.main.diet;

import com.firstproject.project.project.main.record.Record;
import com.firstproject.project.project.main.record.RecordDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
    public String Deleteat(@RequestBody DietDto dietDto) {

        String text =   dietService.delete(dietDto);

        return text;
    }

    @Operation(summary = "음식기록 추가")
    @PostMapping("/insert")
    public ResponseEntity<Diet> inserteat(@RequestBody DietDto dietDto){
        dietDto.setDdatetime(LocalDateTime.now());
        ModelMapper mapper = new ModelMapper();
       Diet diet = mapper.map(dietDto, Diet.class);
        dietService.regist(diet);
        return ResponseEntity.status(HttpStatus.OK).body(diet);
    }
    @Operation(summary = "음식기록 수정")
    @PutMapping("")
    public void updatediet(@RequestBody DietDto dietDto){


    }


}
