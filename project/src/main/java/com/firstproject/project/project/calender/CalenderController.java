package com.firstproject.project.project.calender;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.firstproject.project.project.login.UserDto;
import java.util.List;

@RestController
@RequestMapping("/calender")
@RequiredArgsConstructor
public class CalenderController {

    private final CalenderService calenderService;

    @PostMapping("")
    public List<Double> findCaloriesPerMonth(@RequestBody UserDto userDto) {

        return calenderService.findCalorieDifferencePerMonth(userDto.getId());
    }
}
