package com.main.demo.main.eat;

import com.main.demo.main.table.Diet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


// 운동 및 소모칼로리 계산 인터페이스

@Repository
public interface EatRepository extends JpaRepository<Diet, String> {

    //이번주 섭취 칼로리량
    @Query("SELECT SUM(dcalories) FROM Diet WHERE YEARWEEK(ddatetime) = YEARWEEK(NOW()) AND id = :id")
    int findDcById(@Param("id") String id);
    //지난주 섭취 칼로리
    @Query("SELECT SUM(dcalories) FROM Diet WHERE ddatetime BETWEEN :startDate AND :endDate AND id = :id")
    int findCalculatedCaloriesByLastWeekAndId(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            @Param("id") String id);
    //오늘 섭취 음식
    @Query("SELECT dname FROM Diet WHERE YEARWEEK(ddatetime) = YEARWEEK(NOW()) AND DATE(ddatetime) = CURDATE()AND id = :id")
    List<String> name(@Param("id") String id);

    //오늘 섭취 음식들의 각 칼로리
    @Query("SELECT dcalories FROM Diet WHERE YEARWEEK(ddatetime) = YEARWEEK(NOW())AND DATE(ddatetime) = CURDATE() AND id = :id")
    List<Integer> calories(@Param("id") String id);

    @Query("SELECT SUM(dcalories) FROM Diet  WHERE YEARWEEK(ddatetime) = YEARWEEK(NOW())AND DATE(ddatetime) = CURDATE() AND id = :id")
    int daycalories(@Param("id") String id);



    //해당 날짜의 섭취 음식 정보 삭제
    @Modifying
    @Query("DELETE FROM Diet WHERE id = :id AND dname = :dname AND DATE(ddatetime) = DATE(:date)")
    void deleteByIdAndEnameAndRdatetime(String id, String dname, String date);


    //해당 날짜 삭제
    @Modifying
    @Query("DELETE FROM Diet WHERE id = :id AND DATE(ddatetime) = DATE(:date)")
    void deleteByIdAndRdatetime(String id, String date);




}







