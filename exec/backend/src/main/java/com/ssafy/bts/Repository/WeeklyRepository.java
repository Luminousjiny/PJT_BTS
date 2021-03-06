package com.ssafy.bts.Repository;

import com.ssafy.bts.Domain.User.User;
import com.ssafy.bts.Domain.Weekly.Weekly;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface WeeklyRepository extends JpaRepository<Weekly, Long> {
    Weekly findByWeekId(int weekId);

    @Query("select w from Weekly w where w.user = :user and w.weekDate >= :startDate and w.weekDate >= :startDate and w.weekDate <= :endDate order by w.weekDate, w.weekStartTime asc")
    List<Weekly> findThisWeekly(User user, Date startDate, Date endDate);
}
