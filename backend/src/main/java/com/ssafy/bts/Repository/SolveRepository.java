package com.ssafy.bts.Repository;


import com.ssafy.bts.Domain.Coding.Problem;
import com.ssafy.bts.Domain.Coding.Solve;
import com.ssafy.bts.Domain.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolveRepository  extends JpaRepository<Solve, Long> {
    Solve findByProblemAndUser(Problem problem, User user);
}
