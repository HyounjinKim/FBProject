package com.main.demo.main.work;


import com.main.demo.main.table.Record;
import com.main.demo.main.table.RecordDTO;
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
public class WorkService {
    private final WorkRepository workRepository;

    public List<Record> Week(String id) {
        List<String> ename = workRepository.name(id);
        List<Integer> daytime = workRepository.time(id);
        List<Integer> daytotal = workRepository.calories(id);
        //이번주 소모 칼로리
        Integer week = workRepository.findEMinById(id);
        LocalDate now = LocalDate.now();
        LocalDateTime endOfLastWeek = now.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY)).minusWeeks(1).atStartOfDay();
        LocalDateTime startOfLastWeek = endOfLastWeek.minusDays(6);
//지난주 운동 정보
        Integer last = workRepository.findCalculatedEMinByLastWeekAndId(startOfLastWeek, endOfLastWeek, id);

        List<Record> list = new ArrayList<>();
        if (last == null) {
            last = 0;

        }
        if (week == null) {
            week = 0;
        }
        for (int i = 0; i < ename.size(); i++) {
            list.add(Record.builder()
                    .ename(ename.get(i))
                    .dayemin(daytime.get(i))
                    .daycalories(daytotal.get(i))
                    .lastwork(last)
                    .weekwork(week)
                    .build());
        }
        if (ename.size() == 0) {
            list.add(Record.builder()
                    .lastwork(last)
                    .weekwork(week)
                    .build());
        }

        return list;
    }

    @Transactional
    public void delete(String id, String eName, String date) {
        if (eName != null) {
            workRepository.deleteByIdAndEnameAndRdatetime(id, eName, date);
        } else {
            workRepository.deleteByIdAndRdatetime(id, date);
        }
    }

    @Transactional
    public Record regist(Record record) {

        Record dbrecord = workRepository.findByIdAndAndEname(record.getId(), record.getEname());

        if (dbrecord == null) {
            return workRepository.save(record);
        } else {
            workRepository.updateRecord(record.getId(), record.getEname(), record.getEmin());
            return record;
        }
    }


    public void update(RecordDTO recordDTo, String rename, Integer retime) {


        if (recordDTo.getEname() != null) {
            if (retime != null && rename != null) {
                //운동 이름 운동 시간 바꾸기

            } else if (retime == null) {
                //운동 이름바꾸기

            } else if (rename == null) {
                //운동시간 바꾸기

            } else {
                //에러

            }
        } else {
            //운동명이 비었으니 에러


        }
    }
}















