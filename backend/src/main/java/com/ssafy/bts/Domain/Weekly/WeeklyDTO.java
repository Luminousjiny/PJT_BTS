package com.ssafy.bts.Domain.Weekly;

import com.ssafy.bts.Domain.User.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeeklyDTO {
    private int weekId;
    private UserDTO user;
    private int weekYear;
    private int weekMonth;
    private int weekDate;
    private Date weekStartTime;
    private Date weekEndTime;
    private String weekContent;

    public WeeklyDTO(Weekly weekly){
        this.weekId = weekly.getWeekId();
        if(weekly.getUser() != null){
            user = new UserDTO();
            this.user.setUserId(weekly.getUser().getUserId());
        }
        this.weekYear = weekly.getWeekYear();
        this.weekMonth = weekly.getWeekMonth();
        this.weekDate = weekly.getWeekDate();
        this.weekStartTime = weekly.getWeekStartTime();
        this.weekEndTime = weekly.getWeekEndTime();
        this.weekContent = weekly.getWeekContent();
    }
}
