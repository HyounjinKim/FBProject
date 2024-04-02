package com.firstproject.project.project.main.record;

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
public interface RecordRepository extends JpaRepository<Record, String> {

    //이번주 총 소모 칼로리 계산
    @Query("SELECT SUM((a.emin * b.ecalories)) FROM Record a JOIN Exercise b ON a.ename = b.ename WHERE YEARWEEK(a.rdatetime) = YEARWEEK(NOW()) AND a.id = :id ")
    Integer findEMinById(@Param("id") String id);

    //지난주 소모 칼로리 계산
    @Query("SELECT SUM((a.emin * b.ecalories)) FROM Record a JOIN Exercise b ON a.ename = b.ename WHERE a.rdatetime BETWEEN :startDate AND :endDate AND a.id = :id ")
    Integer findCalculatedEMinByLastWeekAndId(@Param("startDate") LocalDateTime startDate,
                                              @Param("endDate") LocalDateTime endDate,
                                              @Param("id") String id);

    //오늘
    // 운동명 가져오기
    @Query("SELECT a.ename FROM Record a JOIN Exercise b ON a.ename = b.ename WHERE YEARWEEK(a.rdatetime) = YEARWEEK(NOW()) AND DATE(a.rdatetime) = CURDATE() AND a.id = :id GROUP BY a.ename")
    List<String> name(@Param("id") String id);

    //운동시간
    @Query("SELECT SUM(a.emin) AS total_e_min FROM Record a JOIN Exercise b ON a.ename = b.ename WHERE YEARWEEK(a.rdatetime) = YEARWEEK(NOW()) AND DATE(a.rdatetime) = CURDATE() AND a.id = :id GROUP BY a.ename")
    List<Integer> time(@Param("id") String id);

    //소모칼로리
    @Query("SELECT SUM(a.emin * b.ecalories) AS total_calories FROM Record a JOIN Exercise b ON a.ename = b.ename WHERE YEARWEEK(a.rdatetime) = YEARWEEK(NOW()) AND DATE(a.rdatetime) = CURDATE() AND a.id = :id GROUP BY a.ename ")
    List<Integer> calories(@Param("id") String id);


    // 해당 날짜의 운동 정보 삭제
    @Transactional
    @Modifying
    @Query("DELETE FROM Record WHERE id = :id AND ename = :ename AND DATE_FORMAT(rdatetime, '%Y-%m-%d') = :rdatetime")
    void deleteByIdAndEnameAndRdatetime(@Param("id") String id, @Param("ename") String ename, @Param("rdatetime") String rdatetime);

    // 해당 날짜의 모든 정보 삭제
    @Transactional
    @Modifying
    @Query("DELETE FROM Record WHERE id = :id AND DATE_FORMAT(rdatetime, '%Y-%m-%d') = :rdatetime")
    void deleteByIdAndRdatetime(@Param("id") String id, @Param("rdatetime") String rdatetime);

    //유효성 검사
    // 이미 있는 운동명인지 확인
    @Query("SELECT emin FROM Record WHERE id = :id AND ename = :ename AND DATE_FORMAT(rdatetime, '%Y-%m-%d') = :rdatetime")
    Optional<Record> selectenameday(@Param("id") String id, @Param("ename") String ename, @Param("rdatetime") String date);

    //이 날짜에 운동했는지 확인
    @Query("SELECT emin FROM Record WHERE id = :id AND DATE_FORMAT(rdatetime, '%Y-%m-%d') = :rdatetime")
    List<Integer> selectday(@Param("id") String id, @Param("rdatetime") String rdatetime);

    //이미 있는 운동의 경우 그 운동에 시간 추가
    @Transactional
    @Modifying
    @Query("UPDATE Record SET emin = emin + :emin WHERE id = :id  AND ename = :ename AND DATE(rdatetime) = CURDATE()")
    void updateRecord(@Param("id") String id, @Param("ename") String ename, @Param("emin") int emin);


    //변경시 있는 운동명인지 확인
    @Query("SELECT ename FROM Record WHERE id = :id AND ename = :rename AND DATE(rdatetime) = CURDATE()")
    Optional<Record> renametest(@Param("id") String id, @Param("rename") String rename);

    //운동 이름 시간 바꾸기
    @Transactional
    @Modifying
    @Query("UPDATE Record SET ename = :rename, emin = :retime WHERE id = :id AND ename = :ename AND DATE(rdatetime) = CURDATE()")
    void updateAll(@Param("id") String id, @Param("ename") String ename, @Param("rename") String rename, @Param("retime") int retime);

    //운동 시간바꾸기
    @Transactional
    @Modifying
    @Query("UPDATE Record SET emin = :retime WHERE id = :id AND ename = :ename AND DATE(rdatetime) = CURDATE()")
    void updattime(@Param("id") String id, @Param("ename") String ename, @Param("retime") int retime);

    //이미 있는 운동명으로 변경시 운동합치기
    //운동명 변경시 운동합치기
    @Transactional
    @Modifying
    @Query("UPDATE Record SET emin = emin + :summin WHERE id = :id AND ename = :rename AND DATE(rdatetime) = CURDATE()")
    void updateExistingEnameWithTime(@Param("id") String id, @Param("rename") String rename, @Param("summin") int summin);

    //운동명 바꿀면서 합쳐질때 운동시간을 미리 저장하는 쿼리문
    @Query("SELECT emin FROM Record WHERE id = :id AND ename = :ename AND DATE(rdatetime) = CURDATE()")
    int emin(@Param("id") String id, @Param("ename") String ename);

    //운동명만 바꾸기
    @Transactional
    @Modifying
    @Query("UPDATE Record SET ename = :rename WHERE id = :id AND ename = :ename AND DATE(rdatetime) = CURDATE()")
    void updateExistingEnameWithTime(@Param("id") String id, @Param("ename") String ename, @Param("rename") String rename);

    //이미 있는 운동명으로 변경 변경된 운동시간
    @Transactional
    @Modifying
    @Query("UPDATE Record SET emin = emin + :retime WHERE id = :id  AND ename = :rename AND DATE(rdatetime) = CURDATE()")
    void updaterenameretime(@Param("id") String id, @Param("rename") String rename, @Param("retime") int retime);

    //운동명  변경시 변경전 운동기록 삭제
    @Transactional
    @Modifying
    @Query("DELETE From Record  WHERE id = :id  AND ename = :ename AND DATE(rdatetime) = CURDATE()")
    void deleteoverlap(@Param("id") String id, @Param("ename") String ename);

    //유효성 검사
    @Query("SELECT r FROM Record r WHERE r.id = :id AND r.ename = :ename AND DATE(r.rdatetime) = CURDATE()")
    Record findByIdAndEname(@Param("id") String id, @Param("ename") String ename);
}
