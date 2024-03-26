package com.mh.restaoi01.canlender;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CalenderService {

    private final CalenderRepository calenderRepository;


    public List<String> findRecordPermonth(String id) {
        List<String> records = calenderRepository.calenderFKsql(id);
        List<String> foodrecords = calenderRepository.calenderfoodsql(id);

        List<String> combineerecords = new ArrayList<>();
        combineerecords.addAll(records);
        System.out.println(records);
        combineerecords.addAll(foodrecords);
        System.out.println(foodrecords);
        System.out.println(combineerecords);

        return combineerecords;
    }


}
