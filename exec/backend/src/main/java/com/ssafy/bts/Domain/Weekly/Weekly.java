package com.ssafy.bts.Domain.Weekly;

import com.ssafy.bts.Controller.Request.WeeklyRequest;
import com.ssafy.bts.Domain.User.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Weekly {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int weekId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date weekDate;

    @Column(nullable = false)
    @Temporal(TemporalType.TIME)
    private Date weekStartTime;

    @Column(nullable = false)
    @Temporal(TemporalType.TIME)
    private Date weekEndTime;

    @Column(nullable = false, columnDefinition = "text")
    private String weekContent;

    public static Weekly createWeekly(WeeklyRequest request) throws ParseException {
        Weekly weeklyInput = new Weekly();
        weeklyInput.setWeekContent(request.getWeekContent());


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(request.getWeekDate());
        cal.add(Calendar.HOUR, -9);
        String weekDate = sdf.format(cal.getTime());
        weeklyInput.setWeekDate(new SimpleDateFormat("yyyy-MM-dd").parse(weekDate));


        sdf = new SimpleDateFormat("HH:mm:ss");
        cal.setTime(request.getWeekStartTime());
        cal.add(Calendar.HOUR, -9);
        String startTime = sdf.format(cal.getTime());

        weeklyInput.setWeekStartTime(new SimpleDateFormat("HH:mm:ss").parse(startTime));

        cal.setTime(request.getWeekEndTime());
        cal.add(Calendar.HOUR, -9);
        String endTime = sdf.format(cal.getTime());

        weeklyInput.setWeekEndTime(new SimpleDateFormat("HH:mm:ss").parse(endTime));

        return weeklyInput;
    }
}
