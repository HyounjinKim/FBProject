package com.mh.restaoi01.canlender;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CalenderRepository extends JpaRepository<CalenderRecrod, String> {

    @Query("SELECT * FROM record WHERE id = 'ab001'")
    public List<String> calendersql(String id);


}
