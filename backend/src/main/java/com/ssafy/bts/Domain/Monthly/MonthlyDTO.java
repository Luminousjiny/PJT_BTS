package com.ssafy.bts.Domain.Monthly;

import com.ssafy.bts.Domain.Room.RoomDTO;
import com.ssafy.bts.Domain.User.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MonthlyDTO {
    private int monId;
    private UserDTO user;
    private int monYear;
    private int monMonth;
    private Date monStartDate;
    private Date monEndDate;
    private String monContent;
    private int monColor;

    public MonthlyDTO(Monthly monthly){
        this.monId = monthly.getMonId();

        if(monthly.getUser() != null){
            user = new UserDTO();
            this.user.setUserId(monthly.getUser().getUserId());
        }
        this.monYear = monthly.getMonYear();
        this.monMonth = monthly.getMonMonth();
        this.monStartDate = monthly.getMonStartDate();
        this.monEndDate = monthly.getMonEndDate();
        this.monContent = monthly.getMonContent();
        this.monColor = monthly.getMonColor();
    }
}
