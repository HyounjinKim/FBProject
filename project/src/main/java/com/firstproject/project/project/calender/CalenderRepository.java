package com.firstproject.project.project.calender;

import com.firstproject.project.project.login.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CalenderRepository extends JpaRepository<User, String> {

    //하루치 캘린더리포지토리
    @Query(value = "SELECT d.dcalories " +
            "FROM diet d " +
            "WHERE d.id = :id AND d.ddatetime = :datetime",
            nativeQuery = true)
    Optional<String> findDietCalories(@Param("id") String id, @Param("datetime") String datetime);

    @Query(value = "SELECT COALESCE(SUM(e.ecalories * r.emin), 0) " +
            "FROM record r " +
            "LEFT JOIN exercise e ON r.ename = e.ename " +
            "WHERE r.id = :id AND r.rdatetime = :datetime",
            nativeQuery = true)
    Optional<String> findExerciseCalories(@Param("id") String id, @Param("datetime") String datetime);


    //한달치 캘린더리포지토리
    @Query(value = "SELECT SUM(dcalories) FROM diet WHERE id = :id AND ddatetime >= DATE_SUB(CURDATE(), INTERVAL 1 MONTH)",nativeQuery = true)
    Optional<Double> dietmonth(@Param("id") String id);

    @Query(value = "SELECT COALESCE(SUM(e.ecalories * r.emin), 0) FROM record r LEFT JOIN exercise e ON r.ename = e.ename WHERE r.id = :id AND rdatetime >= DATE_SUB(CURDATE(), INTERVAL 1 MONTH)",nativeQuery = true)
    Optional<Double> exermonth(@Param("id") String id);
}
