package com.mh.restaoi01.canlender;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface CalenderRepository extends JpaRepository<CalenderRecrod, String> {

//    @Query(value = "SELECT * FROM record r WHERE id = 'ab001'",nativeQuery = true)
    @Query(value = "SELECT e_calories*exercise_min AS calresult FROM exercise ex , record re WHERE ex.e_name = re.exercise_name AND id ='ab001'",nativeQuery = true)
    public List<String> calenderFKsql(String id);

    @Query(value = "SELECT meal_calories FROM diet WHERE id = 'ab001' ORDER BY meal_date",nativeQuery = true)
    public List<String> calenderfoodsql(String id);


}
