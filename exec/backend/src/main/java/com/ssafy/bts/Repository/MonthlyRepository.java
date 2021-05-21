package com.ssafy.bts.Repository;

import com.ssafy.bts.Domain.Monthly.Monthly;
import com.ssafy.bts.Domain.Room.Room;
import com.ssafy.bts.Domain.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MonthlyRepository extends JpaRepository<Monthly, Long> {
    Monthly findByMonId(int monId);

    @Query("select m from Monthly m where m.user = :user and m.monYear = :monYear and m.monMonth = :monMonth")
    List<Monthly> findByUserIdAndMonYearAndMonMonth(User user, int monYear, int monMonth);
}
