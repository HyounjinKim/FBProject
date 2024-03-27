package com.main.demo.main.eat;


import com.main.demo.main.table.Diet;
import com.main.demo.main.table.DietDTO;
import com.main.demo.main.table.Record;
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
import java.util.Optional;

@Service
@Component
@RequiredArgsConstructor
public class EatService {

    private final EatRepository eatRepository;

    public List<Diet> Week(String id) {

        //지난주
        LocalDate now = LocalDate.now();
        LocalDateTime endOfLastWeek = now.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY)).minusWeeks(1).atStartOfDay();
        LocalDateTime startOfLastWeek = endOfLastWeek.minusDays(6);

        //지난주 섭취 칼로리
        Integer last = eatRepository.findCalculatedCaloriesByLastWeekAndId(startOfLastWeek, endOfLastWeek, id);

        //이번주 섭취 칼로리
        Integer week = eatRepository.findDcById(id);

        //오늘칼로리
        List<Integer> daycalories = eatRepository.calories(id);

        List<String> eat = eatRepository.name(id);
        Integer day = eatRepository.daycalories(id);
        List<Diet> list = new ArrayList<>();
        //지난주 섭취 기록이 없을 경우
        if (last == null) {
            last = 0;
        }
        //이번주 섭취 기록이 없을 경우
        if (week == null) {
            week = 0;
        }
        if (day == null) {
            day = 0;
        }

        //오늘 섭취한 음식이 있는경우
        for (int i = 0; i < eat.size(); i++) {
            list.add(Diet.builder()
                    .weekcalories(last)
                    .lastcalories(week)
                    .daycalories(day)
                    .dcalories(daycalories.get(i))
                    .dname(eat.get(i))
                    .build());
        }
//오늘 섭취한 음식이 없는경우
        if (eat.size() == 0) {
            list.add(Diet.builder()
                    .weekcalories(last)
                    .lastcalories(week)
                    .build());
        }

        return list;
    }

    @Transactional
    public String delete(DietDTO dietDTO, String date) {

        if (dietDTO.getDname() == null) {
            List<Integer> list = eatRepository.selectday(dietDTO.getId(), date);
            if (list.size() != 0) {
                eatRepository.deleteByIdAndRdatetime(dietDTO.getId(), date);
                return date + "의 기록의 삭제했습니다.";
            } else {
                return "작성하신 기록은 존재하지 않습니다.";
            }
        } else {
            Optional<Diet> list = eatRepository.selectenameday(dietDTO.getId(), dietDTO.getDname(), date);
            if (list.isPresent()) {
                eatRepository.deleteByIdAndEnameAndRdatetime(dietDTO.getId(), dietDTO.getDname(), date);
                return date + " " + dietDTO.getDname() + "의 기록의 삭제했습니다.";
            } else {
                return "작성하신 기록은 존재하지 않습니다.";
            }

        }
    }

    public Diet regist(Diet dite) {
        return eatRepository.save(dite);
    }
}







