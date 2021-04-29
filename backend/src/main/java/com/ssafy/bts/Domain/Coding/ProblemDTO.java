package com.ssafy.bts.Domain.Coding;

import com.ssafy.bts.Domain.User.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProblemDTO {
    private int proId;
    private UserDTO user;
    private String proTitle;
    private String proContent;
    private Date proDate;

    public ProblemDTO(Problem problem){
        this.proId = problem.getProId();
        if(problem.getUser() != null){
            user = new UserDTO();
            this.user.setUserId(problem.getUser().getUserId());
            this.user.setUserNickname(problem.getUser().getUserNickname());
            this.user.setUserImg(problem.getUser().getUserImg());
        }
        this.proTitle = problem.getProTitle();
        this.proContent = problem.getProContent();
        this.proDate = problem.getProDate();
    }
}
