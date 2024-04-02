package com.firstproject.project.project.main.diet;

import jakarta.transaction.Transactional;
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

    //오늘 총 섭취 칼로리
    @Query("SELECT SUM(dcalories) FROM Diet  WHERE YEARWEEK(ddatetime) = YEARWEEK(NOW())AND DATE(ddatetime) = CURDATE() AND id = :id")
    Integer daycalories(@Param("id") String id);


    //해당 날짜의 섭취 음식 정보 삭제
    @Transactional
    @Modifying
    @Query("DELETE FROM Diet WHERE id = :id AND dname = :dname AND DATE(ddatetime) = DATE(:date)")
    void deleteByIdAndEnameAndRdatetime(@Param("id") String id, @Param("dname") String dname, @Param("date") String date);


    //해당 날짜 삭제
    @Transactional
    @Modifying
    @Query("DELETE FROM Diet WHERE id = :id AND DATE(ddatetime) = DATE(:date)")
    void deleteByIdAndRdatetime(@Param("id") String id, @Param("date") String date);


    //유효성 검사
    //해당날짜의 음식이 있는지
    // 이미 있는 음식명인지 확인
    @Query("SELECT dcalories FROM Diet WHERE id = :id AND dname = :dname AND DATE_FORMAT(ddatetime, '%Y-%m-%d') = :ddatetime")
    Optional<Diet> selectenameday(@Param("id") String id, @Param("dname") String dname, @Param("ddatetime") String date);

    //해당 날짜에 값있는지
    @Query("SELECT dcalories FROM Diet WHERE id = :id AND DATE_FORMAT(ddatetime, '%Y-%m-%d') = :ddatetime")
    List<Integer> selectday(@Param("id") String id, @Param("ddatetime") String date);


    //이미 있는 음식명인지 확인
    @Query("SELECT d FROM Diet d WHERE d.id = :id AND d.dname = :dname AND DATE(d.ddatetime) = CURDATE()")
    Diet findByIdAndDname(@Param("id") String id, @Param("dname") String dname);

    //이미 있는 음식명인 경우 작성한 칼로리 합치기
    @Transactional
    @Modifying
    @Query("UPDATE Diet SET dcalories = dcalories + :dcalorie WHERE id = :id  AND dname = :dname AND DATE(ddatetime) = CURDATE()")
    void updatedite(@Param("id") String id, @Param("dname") String dname, @Param("dcalorie") float dcalorie);


    //변경시 이미 있는 음식명인지 확인
    @Query("SELECT dname FROM Diet WHERE id = :id AND dname = :rename AND DATE(ddatetime) = CURDATE()")
    Optional<Diet> renametest(@Param("id") String id, @Param("rename") String rename);

    //이미 있는 음식명으로 변경시 바꾸기전 음식의 칼로리추출
    @Query("SELECT dcalories FROM Diet WHERE id = :id AND dname = :dname AND DATE(ddatetime) = CURDATE()")
    int thiscalories(@Param("id") String id, @Param("dname") String dname);

    //이미 있는 음식명으로 변경시 칼로리를 더해주기만
    @Transactional
    @Modifying
    @Query("UPDATE Diet SET dcalories = dcalories+ :sumcalories WHERE id = :id AND dname = :rename AND DATE(ddatetime) = CURDATE()")
    void updateExistingDnameWithsumCalories(@Param("id") String id, @Param("rename") String newName, @Param("sumcalories") int sumcalories);

    //이미 있는 음식명으로 변경시  변경전 음식기록 삭제
    @Transactional
    @Modifying
    @Query("DELETE From Diet  WHERE id = :id  AND dname = :dname AND DATE(ddatetime) = CURDATE()")
    void deleteoverlap(@Param("id") String id, @Param("dname") String dname);

    //음식명만 바꾸기
    @Transactional
    @Modifying
    @Query("UPDATE Diet SET dname = :rename WHERE id = :id AND dname = :dname AND DATE(ddatetime) = CURDATE()")
    void updateExistingEnameWithTime(@Param("id") String id, @Param("dname") String dname, @Param("rename") String newName);

    //음식 칼로리만 변경
    @Transactional
    @Modifying
    @Query("UPDATE Diet SET dcalories = :recalories WHERE id = :id AND dname = :dname AND DATE(ddatetime) = CURDATE()")
    void updatcalories(@Param("id") String id, @Param("dname") String dname, @Param("recalories") float recalories);

    //이미 있는 음식인 경우 칼로리 합치기
    @Transactional
    @Modifying
    @Query("UPDATE Diet SET dcalories = dcalories + :recalories WHERE id = :id  AND dname = :rename AND DATE(ddatetime) = CURDATE()")
    void updaterenamerecalories(@Param("id") String id, @Param("rename") String newName, @Param("recalories") float recalories);

    //음식명 칼로리 변경
    @Transactional
    @Modifying
    @Query("UPDATE Diet SET dname = :rename, dcalories = :recalories WHERE id = :id AND dname = :dname AND DATE(ddatetime) = CURDATE()")
    void updateAll(@Param("id") String id, @Param("dname") String dname, @Param("rename") String newName, @Param("recalories") float newCalories);
}
