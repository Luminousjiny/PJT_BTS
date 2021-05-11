package com.ssafy.bts.Domain.Coding;

import com.ssafy.bts.Domain.Room.Room;
import com.ssafy.bts.Domain.Room.RoomDTO;
import com.ssafy.bts.Domain.User.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CodeDTO {
    private int codeId;
    private RoomDTO room;
    private ProblemDTO problem;
    private UserDTO user;
    private String codeContent;
    private String codeMemory;
    private String codeTime;
    private String codeLan;
    private Date codeDate;

    public CodeDTO(Code code){
        this.codeId = code.getCodeId();

        if(code.getRoom() != null){
            room = new RoomDTO();
            this.room.setRoomId(code.getRoom().getRoomId());
        }

        if(code.getProblem() != null){
            problem = new ProblemDTO();
            this.problem.setProId(code.getProblem().getProId());
        }
        if(code.getUser() != null){
            user = new UserDTO();
            this.user.setUserId(code.getUser().getUserId());
            this.user.setUserNickname(code.getUser().getUserNickname());
            this.user.setUserImg(code.getUser().getUserImg());
            this.user.setUserLank(code.getUser().getUserLank());
            this.user.setUserPoint(code.getUser().getUserPoint());
        }
        this.codeContent = code.getCodeContent();
        this.codeMemory = code.getCodeMemory();
        this.codeTime = code.getCodeTime();
        this.codeLan = code.getCodeLan();
        this.codeDate = code.getCodeDate();
    }
}
