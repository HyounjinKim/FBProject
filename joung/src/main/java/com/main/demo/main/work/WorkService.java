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
        //지난주 운동기록이 없을 경우
        if (last == null) {
            last = 0;

        }
        //이번주 운동기록이 없을 경우
        if (week == null) {
            week = 0;
        }
        //오늘 운동한 기록이 있을 경우
        for (int i = 0; i < ename.size(); i++) {
            list.add(Record.builder()
                    .ename(ename.get(i))
                    .dayemin(daytime.get(i))
                    .daycalories(daytotal.get(i))
                    .lastwork(last)
                    .weekwork(week)
                    .build());
        }
        //오늘 운동한 기록이 없을 경우
        if (ename.size() == 0) {
            list.add(Record.builder()
                    .lastwork(last)
                    .weekwork(week)
                    .build());
        }

        return list;
    }


    @Transactional
    public Record regist(Record record) {

        Record dbrecord = workRepository.findByIdAndEname(record.getId(), record.getEname());

        if (dbrecord == null) {
            return workRepository.save(record);
        } else {
            workRepository.updateRecord(record.getId(), record.getEname(), record.getEmin());
            return record;
        }
    }

    @Transactional
    public void update(RecordDTO recordDTo, String rename, String retime) {
        Record dbrecord = workRepository.findByIdAndEname(recordDTo.getId(), recordDTo.getEname());

        if (dbrecord != null) {
            if (recordDTo.getEname() != null) {
                if (retime != null && rename != null) {
                    workRepository.updateAll(recordDTo.getId(), recordDTo.getEname(), rename, retime);
                    //운동 이름 운동 시간 바꾸기
                } else if (retime == null) {
                    //운동 이름바꾸기
                    workRepository.updatname(recordDTo.getId(), recordDTo.getEname(), rename);
                } else if (rename == null) {
                    //운동시간 바꾸기
                    workRepository.updattime(recordDTo.getId(), recordDTo.getEname(), retime);
                } else {
                    //운동 이름만적어서 에러
                }
            }
        } else {
            //유효성 검사해서 결과가 없을때
        }

    }

    @Transactional
    public String delete(RecordDTO recordDTO, String date) {

        if (recordDTO.getEname() == null) {
            List<Integer> list = workRepository.selectday(recordDTO.getId(), date);
            if (list.size() != 0) {
                workRepository.deleteByIdAndRdatetime(recordDTO.getId(), date);
                return  date +" 의 기록을 삭제 했습니다.";
            } else {
               return "작성하신 기록은 존재하지 않습니다.";
            }
        } else {
            Optional<Record> list = workRepository.selectenameday(recordDTO.getId(), recordDTO.getEname(), date);
            if (list.isPresent()) {
                workRepository.deleteByIdAndEnameAndRdatetime(recordDTO.getId(), recordDTO.getEname(), date);
                return date + " " +recordDTO.getEname()+" 의 기록을 삭제 했습니다.";
            } else {
                return "작성하신 기록은 존재하지 않습니다.";
            }
        }
    }

}












