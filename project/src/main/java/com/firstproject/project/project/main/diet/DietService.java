package com.firstproject.project.project.main.diet;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Component
@RequiredArgsConstructor
public class DietService {

    private final DietRepository dietRepository;

    public String Week(String id) {
        StringBuilder daysBuilder = new StringBuilder();

        //지난주
        LocalDate now = LocalDate.now();
        LocalDate endOfLastWeek = now.with(TemporalAdjusters.previous(DayOfWeek.SUNDAY));
        LocalDate startOfLastWeek = endOfLastWeek.with(TemporalAdjusters.previous(DayOfWeek.MONDAY));

        //지난주 섭취 칼로리
        Integer last = dietRepository.findCalculatedCaloriesByLastWeekAndId(startOfLastWeek.atStartOfDay(), endOfLastWeek.atStartOfDay(), id);

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

            String name = eat.get(i);
            int days = daycalories.get(i);
            daysBuilder.append(name).append(" ").append(days).append("calorie").append("\n");

        }
        //오늘 섭취한 음식이 없는경우
        if (eat.isEmpty()) {
            daysBuilder.append("오늘 섭취 칼로리 ").append(day).append(" calorie").append("\n").append("지난주 섭취 칼로리 ").append(last).append(" calorie ").append("\n").append("이번주 섭취 칼로리 ").append(week).append(" calorie ");
        } else {
            daysBuilder.append("오늘 섭취 칼로리 ").append(day).append(" calorie ").append("\n").append("지난주 섭취 칼로리 ").append(last).append(" calorie ").append("\n").append("이번주 섭취 칼로리 ").append(week).append(" calorie ");
        }
        return daysBuilder.toString();
    }

    @Transactional
    public String delete(DietDto dietDto) {

        if (dietDto.getDname().equals("")) {
            List<Integer> list = dietRepository.selectday(dietDto.getId(), dietDto.getDate());
            if (list.size() != 0) {
                dietRepository.deleteByIdAndRdatetime(dietDto.getId(), dietDto.getDate());
                return dietDto.getDate() + "의 기록의 삭제했습니다.";
            } else {
                return "작성하신 기록은 존재하지 않습니다.";
            }
        } else {
            Optional<Diet> list = dietRepository.selectenameday(dietDto.getId(), dietDto.getDname(), dietDto.getDate());
            if (list.isPresent()) {
                dietRepository.deleteByIdAndEnameAndRdatetime(dietDto.getId(), dietDto.getDname(), dietDto.getDate());
                return dietDto.getDate() + " " + dietDto.getDname() + "의 기록의 삭제했습니다.";
            } else {
                return "작성하신 기록은 존재하지 않습니다.";
            }
        }
    }

    public Diet regist(Diet dite) {

        Diet dbdiet = dietRepository.findByIdAndDname(dite.getId(), dite.getDname());

        if (dbdiet == null) {
            return dietRepository.save(dite);
        } else {
            dietRepository.updatedite(dbdiet.getId(), dbdiet.getDname(), dite.getDcalories());
            return dbdiet;
        }
    }


    public String update(Diet diet) {
        //유효성 검사
        Diet dbdiet = dietRepository.findByIdAndDname(diet.getId(), diet.getDname());

        // 운동 기록이 존재하는 경우
        if (dbdiet != null) {
            // 새로운 운동 이름과 칼로리를 받아옴

            // 새로운 음식 이름과 칼로리 모두 있는 경우
            if (diet.getRename() != null && diet.getRecalories() != 0) {
                // 새로운 음식 이름이 이미 존재하는 경우
                Optional<Diet> testname = dietRepository.renametest(diet.getId(), diet.getRename());
                if (testname.isPresent()) {
                    // 이미 있는 음식이면 합치기
                    dietRepository.updaterenamerecalories(diet.getId(), diet.getRename(), diet.getRecalories());
                    dietRepository.deleteoverlap(diet.getId(), diet.getDname());
                    return "해당 음식명과 칼로리가 합쳐졌습니다.";
                } else {
                    // 이미 없는 경우 새로운 음식 이름과 칼로리로 업데이트
                    dietRepository.updateAll(diet.getId(), diet.getDname(), diet.getRename(), diet.getRecalories());
                    return "해당 음식명과 칼로리가 변경 되었습니다.";
                }
            }
            // 새로운 음식 이름만 있는 경우
            else if (diet.getRename() != null) {
                // 새로운 음식 이름이 이미 존재하는 경우
                Optional<Diet> testname = dietRepository.renametest(diet.getId(), diet.getRename());
                if (testname.isPresent()) {
                    // 이미 있는 음식이 름이면 칼로리만 업데이트
                    int sumcalories = dietRepository.thiscalories(diet.getId(), diet.getDname());
                    dietRepository.updateExistingDnameWithsumCalories(diet.getId(), diet.getRename(), sumcalories);
                    dietRepository.deleteoverlap(diet.getId(), diet.getDname());
                    return "해당 음식명이 합쳐졌습니다.";
                } else {
                    //  없는 경우 새로운 음식 이름으로 업데이트
                    dietRepository.updateExistingEnameWithTime(diet.getId(), diet.getDname(), diet.getRename());
                    return "해당 음식명 변경되었습니다.";
                }
            }
            // 새로운 음식 칼로리만 있는 경우
            else if (diet.getRecalories() != 0) {
                // 음식 칼로리만 업데이트
                dietRepository.updatcalories(diet.getId(), diet.getDname(), diet.getRecalories());
                return "해당 음식의 칼로리가 변경되었습니다.";
            }
            // 새로운 음식 이름과 칼로리 모두 없는 경우
            else {
                return "변경하실 내용을 작성해 주세요.";
            }
        }
        // 음식 기록이 존재하지 않는 경우
        else {
            return "작성하신 기록은 존재하지 않습니다.";
        }
    }
}









