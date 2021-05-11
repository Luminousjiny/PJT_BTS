package com.ssafy.bts.Repository;

import com.ssafy.bts.Domain.Coding.Problem;
import com.ssafy.bts.Domain.Room.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProblemRepository extends JpaRepository<Problem, Long> {
    Problem findByProId(int proId);
    List<Problem> findByProTitleContaining(String keyword);
    List<Problem> findByProContentContaining(String keyword);
    List<Problem> findByRoom(Room room);
}
