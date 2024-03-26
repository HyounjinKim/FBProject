package com.main.demo.main.table;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Entity
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Record")
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rindex;

    private int emin;

    private String ename;

    private String id;


    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime rdatetime;

    @Transient
    private int weekwork;
    @Transient
    private int lastwork;

    @Transient
    private int dayemin;
    @Transient
    private int daycalories;


}
