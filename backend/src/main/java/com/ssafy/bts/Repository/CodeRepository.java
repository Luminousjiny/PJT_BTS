package com.ssafy.bts.Repository;

import com.ssafy.bts.Domain.Coding.Code;
import com.ssafy.bts.Domain.Coding.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CodeRepository extends JpaRepository<Code, Long> {
    Code findByCodeId(int codeId);
    List<Code> findByProblem(Problem problem);
}
