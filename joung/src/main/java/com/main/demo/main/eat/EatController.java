package com.main.demo.main.eat;


import com.main.demo.main.table.Diet;
import com.main.demo.main.table.DietDTO;
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
public class EatController {

    private final EatService eatService;
    @PostMapping("")
    public ResponseEntity<List<Diet>> week(@RequestBody DietDTO dietDTO) {
        List<Diet> list = eatService.Week(dietDTO.getId());

            return ResponseEntity.status(HttpStatus.OK).body(list);

    }


    @DeleteMapping("")
    public String Deleteat(@RequestBody Map<String, String> requestBody) {
        DietDTO dietDTO = new DietDTO();
        String id = requestBody.get("id");
        String dname = requestBody.get("dname");

        dietDTO.setId(id);
        dietDTO.setDname(dname);
        String date = requestBody.get("date");

    String text =   eatService.delete(dietDTO, date);

    return text;
    }
        @PostMapping("/insert")
        public ResponseEntity<Diet> inserteat(@RequestBody Diet dite){
            dite.setDdatetime(LocalDateTime.now());
            eatService.regist(dite);
            return ResponseEntity.status(HttpStatus.OK).body(dite);
    }

}