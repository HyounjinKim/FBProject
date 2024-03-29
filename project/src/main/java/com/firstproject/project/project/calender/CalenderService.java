package com.firstproject.project.project.calender;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CalenderService {

    private final CalenderRepository calenderRepository;

    public List<Double> findCalorieDifferencePerMonth(String id) {
        System.out.println("서비스 id" + id);
        List<Object[]> results = calenderRepository.findCalorieDifferencePerDay(id);
        List<Double> calorieDifferences = new ArrayList<>();

        for (Object[] result : results) {
            Double calorieDifference = (Double) result[0];
            calorieDifferences.add(calorieDifference);
        }
        return calorieDifferences;
    }
}
