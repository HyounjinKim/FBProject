package com.main.demo.main.table;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;



@Getter
@Setter
@ToString
public class DietDTO {


    private int didx;
    private String id;
    private String dname;
    private LocalDateTime ddatetime;
    private float dcalories;



    public Diet of(DietDTO dietDTO) {
        Diet diet = new Diet();
        diet.setDidx(dietDTO.getDidx());
        diet.setId(dietDTO.getId());
        diet.setDname(dietDTO.getDname());
        diet.setDdatetime(dietDTO.getDdatetime());
        diet.setDcalories(dietDTO.getDcalories());
        return diet;
    }
}
