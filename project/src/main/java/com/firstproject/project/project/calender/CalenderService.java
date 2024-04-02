package com.firstproject.project.project.calender;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CalenderService {

    private final CalenderRepository calenderRepository;

    // 하루치 캘린더서비스
    public String calday(String id, String datetime) {
        Optional<String> dietCaloriesOpt = calenderRepository.findDietCalories(id, datetime);
        Optional<String> exerciseCaloriesOpt = calenderRepository.findExerciseCalories(id, datetime);

        double dietCalories = dietCaloriesOpt.map(Double::parseDouble).orElse(0.0);
        double exerciseCalories = exerciseCaloriesOpt.map(Double::parseDouble).orElse(0.0);

        String dayDietCalories = String.format("%.2f", dietCalories);
        String dayExerciseCalories = String.format("%.2f", exerciseCalories);

        return datetime + "일의 섭취한 칼로리는 " + dayDietCalories + "kcal이고 운동으로 소모한 칼로리는 " + dayExerciseCalories +  "kcal 입니다.";
    }

    // 한달치 캘린더서비스
    public String calmonth(String id) {
        Optional<Double> dietcal = calenderRepository.dietmonth(id);
        Optional<Double> exercal = calenderRepository.exermonth(id);

        double dietmonth = dietcal.orElse(0.0);
        double exercalmonth = exercal.orElse(0.0);

        double summonth = (dietmonth - exercalmonth);
        double reversesummonth = (exercalmonth - dietmonth);

        String f2DietMonth = String.format("%.2f", dietmonth);
        String f2ExerMonth = String.format("%.2f", exercalmonth);
        String f2SumMonth = String.format("%.2f", summonth);
        String f2ReverseSumMonth = String.format("%.2f", reversesummonth);

        if (dietmonth > exercalmonth){
            return "한달동안 섭취한 음식 칼로리는 " + f2DietMonth + "kcal이며, " + "운동으로 소비한 칼로리는 " + f2ExerMonth + "kcal입니다." + "음식 섭취 칼로리에서 운동으로 소비한 칼로리를 빼면 " + f2SumMonth + "kcal입니다.";
        }
        else {
            return "한달동안 섭취한 음식 칼로리는 " + f2DietMonth + "kcal이며, " + "운동으로 소비한 칼로리는 " + f2ExerMonth + "kcal입니다." + "음식 섭취 칼로리를 모두 소비하고 추가로 운동으로 소비한 칼로리는 " + f2ReverseSumMonth + "kcal입니다.";
        }
    }
}
