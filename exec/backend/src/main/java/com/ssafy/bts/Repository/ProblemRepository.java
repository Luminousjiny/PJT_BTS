package com.ssafy.bts.Repository;

import com.ssafy.bts.Domain.Coding.Problem;
import com.ssafy.bts.Domain.Room.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProblemRepository extends JpaRepository<Problem, Long> {
    Problem findByProId(int proId);

    @Query("SELECT p FROM Problem p WHERE p.room = :room and p.proTitle LIKE %:keyword%")
    List<Problem> findByProTitleContaining(Room room, String keyword);

    @Query("SELECT p FROM Problem p WHERE p.room = :room and p.proContent LIKE %:keyword%")
    List<Problem> findByProContentContaining(Room room, String keyword);
    List<Problem> findByRoom(Room room);
}
