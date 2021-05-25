package com.ssafy.bts.Domain.Monthly;

import com.ssafy.bts.Controller.Request.MonthlyRequest;
import com.ssafy.bts.Domain.User.User;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Entity
@Getter @Setter
public class Monthly {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int monId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private int monYear;

    @Column(nullable = false)
    private int monMonth;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date monStartDate;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date monEndDate;

    @Column(nullable = false, columnDefinition = "text")
    private String monContent;

    @Column(nullable = false)
    private int monColor;

    public static Monthly createMonthly(MonthlyRequest request) throws ParseException {
        Monthly monthlyInput = new Monthly();
        monthlyInput.setMonYear(request.getMonYear());
        monthlyInput.setMonMonth(request.getMonMonth());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(request.getMonStartDate());
        String startTime = sdf.format(cal.getTime());

        monthlyInput.setMonStartDate(new SimpleDateFormat("yyyy-MM-dd").parse(startTime));

        cal.setTime(request.getMonEndDate());
        String endTime = sdf.format(cal.getTime());

        monthlyInput.setMonEndDate(new SimpleDateFormat("yyyy-MM-dd").parse(endTime));
        monthlyInput.setMonContent(request.getMonContent());
        monthlyInput.setMonColor(request.getMonColor());
        return monthlyInput;
    }
}
