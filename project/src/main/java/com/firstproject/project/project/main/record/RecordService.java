package com.firstproject.project.project.main.record;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Optional;

@Service
@Component
@RequiredArgsConstructor
public class RecordService {
    private final RecordRepository recordRepository;

    public String Week(String id) {
        StringBuilder daysBuilder = new StringBuilder();

        List<String> ename = recordRepository.name(id);
        List<Integer> daytime = recordRepository.time(id);
        List<Integer> daytotal = recordRepository.calories(id);
        // 이번주 소모 칼로리
        Integer week = recordRepository.findEMinById(id);

        LocalDate now = LocalDate.now();
        LocalDate endOfLastWeek = now.with(TemporalAdjusters.previous(DayOfWeek.SUNDAY));
        LocalDate startOfLastWeek = endOfLastWeek.with(TemporalAdjusters.previous(DayOfWeek.MONDAY));
        // 지난주 운동 정보
        Integer last = recordRepository.findCalculatedEMinByLastWeekAndId(startOfLastWeek.atStartOfDay(), endOfLastWeek.atStartOfDay(), id);
        // 지난주 운동기록이 없을 경우
        if (last == null) {
            last = 0;
        }
        // 이번주 운동기록이 없을 경우
        if (week == null) {
            week = 0;
        }
        // 오늘 운동한 기록이 있을 경우
        for (int i = 0; i < ename.size(); i++) {
            String name = ename.get(i);
            int day = daytime.get(i);
            int total = daytotal.get(i);
            daysBuilder.append(name).append(" ").append(day).append("분 ").append(total).append(" calorie\n");
        }
        // 오늘 운동한 기록이 없을 경우
        if (ename.isEmpty()) {
            daysBuilder.append("지난주 소모 칼로리 ").append(last).append(" calorie ").append("\n").append(" 이번주 소모 칼로리 ").append(week).append(" calorie ");
        } else {
            daysBuilder.append("지난주 소모 칼로리 ").append(last).append(" calorie ").append("\n").append("이번주 소모 칼로리 ").append(week).append(" calorie ");
        }
        return daysBuilder.toString();

    }

    @Transactional
    public Record regist(Record record) {

        Record dbrecord = recordRepository.findByIdAndEname(record.getId(), record.getEname());

        if (dbrecord == null) {
            return recordRepository.save(record);
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
            // 새로운 운동 이름과 시간이 모두 있는 경우
            if (record.getRename() != null && record.getRetime() != 0) {
                // 새로운 운동 이름이 이미 존재하는 경우
                Optional<Record> testname = recordRepository.renametest(record.getId(), record.getRename());
                if (testname.isPresent()) {
                    // 이미 있는 운동이면 합치기
                    recordRepository.updaterenameretime(record.getId(), record.getRename(), record.getRetime());
                    recordRepository.deleteoverlap(record.getId(), record.getEname());
                    return "해당 운동명과 시간이 합쳐졌습니다.";
                } else {
                    // 이미 없는 경우 새로운 운동 이름과 시간으로 업데이트
                    recordRepository.updateAll(record.getId(), record.getEname(), record.getRename(), record.getRetime());
                    return "해당 운동명과 시간이 변경 되었습니다.";
                }
            }
            // 새로운 운동 이름만 있는 경우
            else if (record.getRename() != null) {
                // 새로운 운동 이름이 이미 존재하는 경우
                Optional<Record> testname = recordRepository.renametest(record.getId(), record.getRename());
                if (testname.isPresent()) {
                    // 이미 있는 운동이면 시간만 업데이트
                    int sumemin = recordRepository.emin(record.getId(), record.getEname());
                    recordRepository.updateExistingEnameWithTime(record.getId(), record.getRename(), sumemin);
                    recordRepository.deleteoverlap(record.getId(), record.getEname());
                    return "해당 운동의 이름이 합쳐졌습니다.";
                } else {
                    //  없는 경우 새로운 운동 이름으로 업데이트
                    recordRepository.updateExistingEnameWithTime(record.getId(), record.getEname(), record.getRename());
                    return "해당 운동의 이름이 변경되었습니다.";
                }
            }
            // 새로운 운동 시간만 있는 경우
            else if (record.getRetime() != 0) {
                // 운동 시간만 업데이트
                recordRepository.updattime(record.getId(), record.getEname(), record.getRetime());
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

        if (recordDTO.getEname().equals("")) {
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



