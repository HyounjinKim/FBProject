package com.firstproject.project.project.calender;

import com.firstproject.project.project.login.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface CalenderRepository extends JpaRepository<User, String> {

    @Query(value = "SELECT (d.dcalories - COALESCE(e.ecalories * r.emin, 0)) AS calorie_difference, r.rdatetime\n" +
            "FROM diet d\n" +
            "LEFT JOIN record r ON (d.id = r.id AND d.ddatetime = r.rdatetime)\n" +
            "LEFT JOIN exercise e ON (r.ename = e.ename AND r.id = :id)\n" +
            "WHERE d.id = :id\n" +
            "ORDER BY r.rdatetime", nativeQuery = true)
    public List<Object[]> findCalorieDifferencePerDay(@Param("id") String id);
}
