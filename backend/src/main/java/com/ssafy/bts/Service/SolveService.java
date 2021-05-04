package com.ssafy.bts.Service;

import com.ssafy.bts.Domain.Coding.Problem;
import com.ssafy.bts.Domain.Coding.Solve;
import com.ssafy.bts.Domain.User.User;
import com.ssafy.bts.Repository.SolveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SolveService {

    private final SolveRepository solveRepository;

    /**
     * 현재 문제를 유저가 이미 풀었는지 조회
     * @return
     */
    public Solve findByProblemAnsUser(Problem problem, User user) { return solveRepository.findByProblemAndUser(problem, user);}

    /**
     * 문제 풀면 문제푼 테이블에 저장
     * @return
     */
    public Solve save(Solve sol) { return solveRepository.save(sol);}
}
