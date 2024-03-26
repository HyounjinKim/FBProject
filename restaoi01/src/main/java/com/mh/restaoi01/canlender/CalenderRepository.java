package com.mh.restaoi01.canlender;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CalenderRepository extends JpaRepository<CalenderRecrod, String> {

    @Query(value = "SELECT * FROM record r WHERE id = 'ab001'",nativeQuery = true)
    public List<String> calenderFKsql(String id);


}
