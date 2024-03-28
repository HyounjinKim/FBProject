package com.main.demo.main.table;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@Setter
@ToString
public class RecordDTO {
    private int rindex;

    private int emin;

    private String ename;

    private String id;

    private LocalDateTime rdatetime;

    private String rename;

    private Integer retime;


    public static Record of(RecordDTO recordDTo) {
        Record record = new Record();
        record.setEname(recordDTo.getEname());
        record.setId(recordDTo.getId());
        record.setEmin(recordDTo.getEmin());
        record.setRdatetime(LocalDate.from(recordDTo.getRdatetime()).atStartOfDay());
        return record;
    }
}

