package com.ssafy.bts.Domain.Info;

import com.ssafy.bts.Domain.User.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InfoDTO {
    private int infoId;
    private UserDTO user;
    private String infoTitle;
    private String infoContent;
    private Date infoDate;

    public InfoDTO(Info info){
        this.infoId = info.getInfoId();
        if(info.getUser() != null){
            user = new UserDTO();
            this.user.setUserId(info.getUser().getUserId());
            this.user.setUserNickname(info.getUser().getUserNickname());
            this.user.setUserImg(info.getUser().getUserImg());
        }
        this.infoTitle = info.getInfoTitle();
        this.infoContent = info.getInfoContent();
        this.infoDate = info.getInfoDate();
    }
}
