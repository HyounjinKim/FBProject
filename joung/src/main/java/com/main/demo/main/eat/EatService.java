package com.main.demo.main.eat;


import com.main.demo.main.table.Diet;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

@Service
@Component
@RequiredArgsConstructor
public class EatService {

    private final EatRepository eatRepository;

    public List<Diet> Week(String id) {


        LocalDate now = LocalDate.now();
        LocalDateTime endOfLastWeek = now.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY)).minusWeeks(1).atStartOfDay();
        LocalDateTime startOfLastWeek = endOfLastWeek.minusDays(6);
//지난주 운동 정보
        int last = eatRepository.findCalculatedCaloriesByLastWeekAndId(startOfLastWeek, endOfLastWeek, id);

        int week = eatRepository.findDcById(id);
        List<Integer> daycalories = eatRepository.calories(id);
        List<String> eat = eatRepository.name(id);
        int day = eatRepository.daycalories(id);


        List<Diet> list = new ArrayList<>();
        for (int i = 0; i < eat.size(); i++) {
            list.add(Diet.builder()
                    .weekcalories(last)
                    .lastcalories(week)
                    .daycalories(day)
                    .dcalories(daycalories.get(i))
                    .dname(eat.get(i))
                    .build());

        }


        return list;
    }

    @Transactional
    public void delete(String id, String dName, String date) {

        if (dName != null) {
            eatRepository.deleteByIdAndEnameAndRdatetime(id, dName, date);
        } else {
            eatRepository.deleteByIdAndRdatetime(id, date);
        }
    }

    public Diet regist(Diet dite) {
        return  eatRepository.save(dite);
    }
}







