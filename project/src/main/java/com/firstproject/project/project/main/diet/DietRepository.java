package com.firstproject.project.project.main.diet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


// 운동 및 소모칼로리 계산 인터페이스

@Repository
public interface DietRepository extends JpaRepository<Diet, String> {

    //이번주 섭취 칼로리량
    @Query("SELECT SUM(dcalories) FROM Diet WHERE YEARWEEK(ddatetime) = YEARWEEK(NOW()) AND id = :id")
    Integer findDcById(@Param("id") String id);

    //지난주 섭취 칼로리
    @Query("SELECT SUM(dcalories) FROM Diet WHERE ddatetime BETWEEN :startDate AND :endDate AND id = :id")
    Integer findCalculatedCaloriesByLastWeekAndId(
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
    Integer daycalories(@Param("id") String id);


    //해당 날짜의 섭취 음식 정보 삭제
    @Modifying
    @Query("DELETE FROM Diet WHERE id = :id AND dname = :dname AND DATE(ddatetime) = DATE(:date)")
    void deleteByIdAndEnameAndRdatetime(String id, String dname, String date);


    //해당 날짜 삭제
    @Modifying
    @Query("DELETE FROM Diet WHERE id = :id AND DATE(ddatetime) = DATE(:date)")
    void deleteByIdAndRdatetime(String id, String date);

    //유효성 검사
    //해당날짜의 운동이 있는지
    // 이미 있는 음식명인지 확인
    @Query("SELECT dcalories FROM Diet WHERE id = :id AND dname = :dname AND DATE_FORMAT(ddatetime, '%Y-%m-%d') = :ddatetime")
    Optional<Diet> selectenameday(@Param("id") String id, @Param("dname") String dname, @Param("ddatetime") String date);

    //해당 날짜에 값있는지
    @Query("SELECT dcalories FROM Diet WHERE id = :id AND DATE_FORMAT(ddatetime, '%Y-%m-%d') = :ddatetime")
    List<Integer> selectday(@Param("id") String id, @Param("ddatetime") String date);


}
