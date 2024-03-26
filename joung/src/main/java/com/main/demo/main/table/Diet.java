package com.main.demo.main.table;


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
    private LocalDateTime ddatetime;
    private float dcalories;


    @Transient
    private int weekcalories;
    @Transient
    private int lastcalories;
    @Transient
    private int daycalories;





}
