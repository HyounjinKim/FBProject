package com.firstproject.project.project.main.diet;

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
public class DietService {

    private final DietRepository dietRepository;

    public List<Diet> Week(String id) {

        //지난주
        LocalDate now = LocalDate.now();
        LocalDateTime endOfLastWeek = now.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY)).minusWeeks(1).atStartOfDay();
        LocalDateTime startOfLastWeek = endOfLastWeek.minusDays(6);

        //지난주 섭취 칼로리
        Integer last = dietRepository.findCalculatedCaloriesByLastWeekAndId(startOfLastWeek, endOfLastWeek, id);

        //이번주 섭취 칼로리
        Integer week = dietRepository.findDcById(id);

        //오늘칼로리
        List<Integer> daycalories = dietRepository.calories(id);

        List<String> eat = dietRepository.name(id);
        Integer day = dietRepository.daycalories(id);
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
    public String delete(DietDto dietDto, String date) {

        if (dietDto.getDname() == null) {
            List<Integer> list = dietRepository.selectday(dietDto.getId(), date);
            if (list.size() != 0) {
                dietRepository.deleteByIdAndRdatetime(dietDto.getId(), date);
                return date + "의 기록의 삭제했습니다.";
            } else {
                return "작성하신 기록은 존재하지 않습니다.";
            }
        } else {
            Optional<Diet> list = dietRepository.selectenameday(dietDto.getId(), dietDto.getDname(), date);
            if (list.isPresent()) {
                dietRepository.deleteByIdAndEnameAndRdatetime(dietDto.getId(), dietDto.getDname(), date);
                return date + " " + dietDto.getDname() + "의 기록의 삭제했습니다.";
            } else {
                return "작성하신 기록은 존재하지 않습니다.";
            }

        }
    }

    public Diet regist(Diet dite) {
        return dietRepository.save(dite);
    }
}
