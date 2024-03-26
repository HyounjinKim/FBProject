package com.mh.restaoi01.canlender;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/calender")
@RequiredArgsConstructor
public class CalenderController {

    private final CalenderService calenderService;
    @PostMapping("")
    public List<String> findRecord(@RequestBody String id){

        List<String> records = calenderService.findRecordPermonth(id);
        return records;
    }
}
