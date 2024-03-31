package com.firstproject.project.project.calender;

import com.firstproject.project.project.login.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CalenderService {

    private final CalenderRepository calenderRepository;

    // 하루치 캘린더서비스
    public String calday(String id, String datetime) {
        Optional<String> dietCaloriesOpt = calenderRepository.findDietCalories(id, datetime);
        Optional<String> exerciseCaloriesOpt = calenderRepository.findExerciseCalories(id, datetime);

        String dietCalories = dietCaloriesOpt.orElse("0.0");
        String exerciseCalories = exerciseCaloriesOpt.orElse("0.0");

        return datetime + "일의 섭취한 칼로리는 " + dietCalories + "kcal이고 운동으로 소모한 칼로리는 " + exerciseCalories +  "kcal 입니다.";
    }

    // 한달치 캘린더서비스
    public String calmonth(String id) {
        Optional<Double> dietcal = calenderRepository.dietmonth(id);
        Optional<Double> exercal = calenderRepository.exermonth(id);

        double dietmonth = dietcal.orElse(0.0);
        double exercalmonth = exercal.orElse(0.0);

        double summonth = (dietmonth - exercalmonth);
        double reversesummonth = (exercalmonth - dietmonth);

        if (dietmonth > exercalmonth){
            return "한달동안 음식으로 섭취한 칼로리는 " + dietmonth + "kcal입니다." + " 한달동안 운동으로 소모한 칼로리는 " + exercalmonth + "kcal입니다." + " 음식으로 섭취한 칼로리에서 운동으로으로 소모한 칼로리를 빼고 남은 칼로리는 " + summonth + "kcal입니다.";
        }
        else {
            return "한달동안 음식으로 섭취한 칼로리는 " + dietmonth + "kcal입니다." + " 한달동안 운동으로 소모한 칼로리는 " + exercalmonth + "kcal입니다." + "음식으로 섭취한 칼로리를 모두 소모시키고 운동으로 추가 소모시킨 칼로리는 " + reversesummonth + "kcal입니다.";        }
    }
}
