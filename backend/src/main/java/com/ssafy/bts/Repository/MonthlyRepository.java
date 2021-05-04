package com.ssafy.bts.Repository;

import com.ssafy.bts.Domain.Monthly.Monthly;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MonthlyRepository extends JpaRepository<Monthly, Long> {
    Monthly findByMonId(int monId);
    List<Monthly> findByMonYearAndMonMonth(int monYear, int monMonth);
}
