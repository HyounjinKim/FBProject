package com.firstproject.project.project.main.record;

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
public class RecordService {
    private final RecordRepository recordRepository;

    public List<Record> Week(String id) {
        List<String> ename = recordRepository.name(id);
        List<Integer> daytime = recordRepository.time(id);
        List<Integer> daytotal = recordRepository.calories(id);
        //이번주 소모 칼로리
        Integer week = recordRepository.findEMinById(id);
        LocalDate now = LocalDate.now();
        LocalDateTime endOfLastWeek = now.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY)).minusWeeks(1).atStartOfDay();
        LocalDateTime startOfLastWeek = endOfLastWeek.minusDays(6);
//지난주 운동 정보
        Integer last = recordRepository.findCalculatedEMinByLastWeekAndId(startOfLastWeek, endOfLastWeek, id);

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

        Record dbrecord = recordRepository.findByIdAndEname(record.getId(), record.getEname());

        if (dbrecord == null) {
            return  recordRepository.save(record);
        } else {
            recordRepository.updateRecord(record.getId(), record.getEname(), record.getEmin());
            return dbrecord;
        }
    }

    @Transactional
    public String update(Record record) {
        Record dbrecord = recordRepository.findByIdAndEname(record.getId(), record.getEname());

        // 운동 기록이 존재하는 경우
        if (dbrecord != null) {
            // 새로운 운동 이름과 시간을 받아옴
            String newName = record.getRename();
            int newTime = record.getRetime();

            // 새로운 운동 이름과 시간이 모두 있는 경우
            if (newName != null && newTime != 0) {
                // 새로운 운동 이름이 이미 존재하는 경우
                Optional<Record> testname = recordRepository.renametest(record.getId(), newName);
                if (testname.isPresent()) {
                    // 이미 있는 운동이면 합치기
                    recordRepository.updaterenameretime(record.getId(), newName, newTime);
                    recordRepository.deleteoverlap(record.getId(), record.getEname());
                    return "해당 운동명과 시간이 합쳐졌습니다.";
                } else {
                    // 이미 없는 경우 새로운 운동 이름과 시간으로 업데이트
                    recordRepository.updateAll(record.getId(), record.getEname(), newName, newTime);
                    return "해당 운동명과 시간이 변경 되었습니다.";
                }
            }
            // 새로운 운동 이름만 있는 경우
            else if (newName != null) {
                // 새로운 운동 이름이 이미 존재하는 경우
                Optional<Record> testname = recordRepository.renametest(record.getId(), newName);
                if (testname.isPresent()) {
                    // 이미 있는 운동이면 시간만 업데이트
                    int sumemin = recordRepository.emin(record.getId(),record.getEname());
                    recordRepository.updateExistingEnameWithTime(record.getId(), newName, sumemin);
                    recordRepository.deleteoverlap(record.getId(), record.getEname());
                    return "해당 운동의 이름이 합쳐졌습니다.";
                } else {
                    //  없는 경우 새로운 운동 이름으로 업데이트
                    recordRepository.updateExistingEnameWithTime(record.getId(),record.getEname(), newName);
                    return "해당 운동의 이름이 변경되었습니다.";
                }
            }
            // 새로운 운동 시간만 있는 경우
            else if (newTime != 0) {
                // 운동 시간만 업데이트
                recordRepository.updattime(record.getId(), record.getEname(), newTime);
                return "해당 운동의 시간이 변경되었습니다.";
            }
            // 새로운 운동 이름과 시간이 모두 없는 경우
            else {
                return "변경하실 내용을 작성해 주세요.";
            }
        }
        // 운동 기록이 존재하지 않는 경우
        else {
            return "작성하신 기록은 존재하지 않습니다.";
        }
    }

    @Transactional
    public String delete(RecordDto recordDTO) {

        if (recordDTO.getEname() == null) {
            List<Integer> list = recordRepository.selectday(recordDTO.getId(), recordDTO.getDate());
            if (list.size() != 0) {
                recordRepository.deleteByIdAndRdatetime(recordDTO.getId(), recordDTO.getDate());
                return recordDTO.getDate() + " 의 기록을 삭제 했습니다.";
            } else {
                return "작성하신 기록은 존재하지 않습니다.";
            }
        } else {
            Optional<Record> list = recordRepository.selectenameday(recordDTO.getId(), recordDTO.getEname(), recordDTO.getDate());
            if (list.isPresent()) {
                recordRepository.deleteByIdAndEnameAndRdatetime(recordDTO.getId(), recordDTO.getEname(), recordDTO.getDate());
                return recordDTO.getDate() + " " + recordDTO.getEname() + " 의 기록을 삭제 했습니다.";
            } else {
                return "작성하신 기록은 존재하지 않습니다.";
            }
        }
    }

}
