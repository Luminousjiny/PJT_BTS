package com.ssafy.bts.Domain.Coding;

import com.ssafy.bts.Domain.User.User;
import com.ssafy.bts.Domain.User.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolveDTO {
    private int solveId;
    private ProblemDTO problem;
    private UserDTO user;

    public SolveDTO(Solve solve){
        this.solveId = solve.getSolveId();
        if(solve.getProblem() != null){
            problem = new ProblemDTO();
            this.problem.setProId(solve.getProblem().getProId());
        }
        if(solve.getUser()!=null){
            user = new UserDTO();
            this.user.setUserId(solve.getUser().getUserId());
        }
    }
}
