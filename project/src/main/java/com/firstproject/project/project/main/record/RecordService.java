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
    public Record regist(RecordDto recordDto) {

        Record dbrecord = recordRepository.findByIdAndEname(recordDto.getId(), recordDto.getEname());

        if (dbrecord == null) {
            return recordRepository.save(dbrecord);
        } else {
            recordRepository.updateRecord(recordDto.getId(), recordDto.getEname(), recordDto.getEmin());
            return dbrecord;
        }
    }

    @Transactional
    public void update(RecordDto recordDTo, String rename, String retime) {
        Record dbrecord = recordRepository.findByIdAndEname(recordDTo.getId(), recordDTo.getEname());

        if (dbrecord != null) {
            if (recordDTo.getEname() != null) {
                if (retime != null && rename != null) {
                    recordRepository.updateAll(recordDTo.getId(), recordDTo.getEname(), rename, retime);
                    //운동 이름 운동 시간 바꾸기
                } else if (retime == null) {
                    //운동 이름바꾸기
                    recordRepository.updatname(recordDTo.getId(), recordDTo.getEname(), rename);
                } else if (rename == null) {
                    //운동시간 바꾸기
                    recordRepository.updattime(recordDTo.getId(), recordDTo.getEname(), retime);
                } else {
                    //운동 이름만적어서 에러
                }
            }
        } else {
            //유효성 검사해서 결과가 없을때
        }

    }

    @Transactional
    public String delete(RecordDto recordDTO, String date) {

        if (recordDTO.getEname() == null) {
            List<Integer> list = recordRepository.selectday(recordDTO.getId(), date);
            if (list.size() != 0) {
                recordRepository.deleteByIdAndRdatetime(recordDTO.getId(), date);
                return date + " 의 기록을 삭제 했습니다.";
            } else {
                return "작성하신 기록은 존재하지 않습니다.";
            }
        } else {
            Optional<Record> list = recordRepository.selectenameday(recordDTO.getId(), recordDTO.getEname(), date);
            if (list.isPresent()) {
                recordRepository.deleteByIdAndEnameAndRdatetime(recordDTO.getId(), recordDTO.getEname(), date);
                return date + " " + recordDTO.getEname() + " 의 기록을 삭제 했습니다.";
            } else {
                return "작성하신 기록은 존재하지 않습니다.";
            }
        }
    }

}
