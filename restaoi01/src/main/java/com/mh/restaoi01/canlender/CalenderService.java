package com.mh.restaoi01.canlender;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CalenderService {

    private final CalenderRepository calenderRepository;


    public List<String> findRecordPermonth(String id) {

        List<String> records = calenderRepository.calendersql(id);
        return records;
    }


}
