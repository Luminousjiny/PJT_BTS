package com.ssafy.bts.Domain.Coding;

import com.ssafy.bts.Domain.Room.RoomDTO;
import com.ssafy.bts.Domain.User.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolveDTO {
    private int solveId;
    private RoomDTO room;
    private ProblemDTO problem;
    private UserDTO user;

    public SolveDTO(Solve solve){
        this.solveId = solve.getSolveId();

        if(solve.getRoom() != null){
            room = new RoomDTO();
            this.room.setRoomId(solve.getRoom().getRoomId());
        }

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
