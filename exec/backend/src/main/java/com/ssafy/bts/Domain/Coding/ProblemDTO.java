package com.ssafy.bts.Domain.Coding;

import com.ssafy.bts.Domain.Room.RoomDTO;
import com.ssafy.bts.Domain.User.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProblemDTO {
    private int proId;
    private RoomDTO room;
    private UserDTO user;
    private String proTitle;
    private String proContent;
    private String proInput;
    private String proOutput;
    private Date proDate;
    private List<CodeDTO> codeList;

    public ProblemDTO(Problem problem){
        this.proId = problem.getProId();

        if(problem.getRoom() != null){
            room = new RoomDTO();
            this.room.setRoomId(problem.getRoom().getRoomId());
        }

        if(problem.getUser() != null){
            user = new UserDTO();
            this.user.setUserId(problem.getUser().getUserId());
            this.user.setUserNickname(problem.getUser().getUserNickname());
            this.user.setUserImg(problem.getUser().getUserImg());
        }
        this.proTitle = problem.getProTitle();
        this.proContent = problem.getProContent();
        this.proInput = problem.getProInput();
        this.proOutput = problem.getProOutput();
        this.proDate = problem.getProDate();

        if(problem.getCodes() != null){
            List<CodeDTO> codeDTOList = new ArrayList<>();
            for(Code code : problem.getCodes()){
                CodeDTO codeDTO = new CodeDTO(code);
                codeDTOList.add(codeDTO);
            }
            this.codeList = codeDTOList;
        }
    }
}
