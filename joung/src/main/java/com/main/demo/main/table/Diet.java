package com.main.demo.main.table;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Diet")
public class Diet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int didx;
    private String id;
    private String dname;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime ddatetime;

    private float dcalories;


    @Transient
    private Integer weekcalories;
    @Transient
    private Integer lastcalories;
    @Transient
    private int daycalories;





}
