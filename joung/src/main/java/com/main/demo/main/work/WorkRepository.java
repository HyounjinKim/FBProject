package com.main.demo.main.work;

import com.main.demo.main.table.Record;
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
public interface WorkRepository extends JpaRepository< Record, String> {

    //이번주 총 소모 칼로리 계산
    @Query("SELECT SUM((a.emin * b.ecalories)) FROM Record a JOIN Exercise b ON a.ename = b.ename WHERE YEARWEEK(a.rdatetime) = YEARWEEK(NOW()) AND a.id = :id")
    int findEMinById(@Param("id") String id);

    //지난주 소모 칼로리 계산
    @Query("SELECT SUM((a.emin * b.ecalories)) FROM Record a JOIN Exercise b ON a.ename = b.ename WHERE a.rdatetime BETWEEN :startDate AND :endDate AND a.id = :id")
    int findCalculatedEMinByLastWeekAndId(@Param("startDate") LocalDateTime startDate,
                                                  @Param("endDate") LocalDateTime endDate,
                                               @Param("id") String id);
    //오늘
    // 운동명 가져오기
    @Query("SELECT a.ename FROM Record a JOIN Exercise b ON a.ename = b.ename WHERE YEARWEEK(a.rdatetime) = YEARWEEK(NOW()) AND DATE(a.rdatetime) = CURDATE() AND a.id = :id GROUP BY a.ename ")
    List<String> name(@Param("id") String id);
    //운동시간
    @Query("SELECT SUM(a.emin) AS total_e_min FROM Record a JOIN Exercise b ON a.ename = b.ename WHERE YEARWEEK(a.rdatetime) = YEARWEEK(NOW()) AND DATE(a.rdatetime) = CURDATE() AND a.id = :id GROUP BY a.ename ORDER BY total_e_min DESC")
    List<Integer> time(@Param("id") String id);
    //소모칼로리
    @Query("SELECT SUM(a.emin * b.ecalories) AS total_calories FROM Record a JOIN Exercise b ON a.ename = b.ename WHERE YEARWEEK(a.rdatetime) = YEARWEEK(NOW()) AND DATE(a.rdatetime) = CURDATE() AND a.id = :id GROUP BY a.ename ")
    List<Integer> calories(@Param("id") String id);



    //해당 날짜의 운동 정보 삭제
    @Modifying
    @Query("DELETE FROM Record WHERE id = :id AND ename = :ename AND DATE_FORMAT(rdatetime, '%Y. %m. %d') = :rdatetime")
    void deleteByIdAndEnameAndRdatetime(@Param("id") String id, @Param("ename") String ename, @Param("rdatetime") String rdatetime);

    // 해당 날짜의 모든 정보 삭제
    @Modifying
    @Query("DELETE FROM Record WHERE id = :id AND DATE_FORMAT(rdatetime, '%Y. %m. %d') = :rdatetime")
    void deleteByIdAndRdatetime(@Param("id") String id, @Param("rdatetime") String rdatetime);





//    UPDATE Record
//    SET emin = emin + 10
//    WHERE id = 'ab001'
//    AND ename = '농구'
//    AND DATE(rdatetime) = CURDATE()
//    LIMIT 1;
}