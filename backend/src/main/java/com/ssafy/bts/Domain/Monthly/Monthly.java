package com.ssafy.bts.Domain.Monthly;

import com.ssafy.bts.Controller.Request.MonthlyRequest;
import com.ssafy.bts.Domain.User.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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

    public static Monthly createMonthly(MonthlyRequest request) {
        Monthly monthlyInput = new Monthly();
        monthlyInput.setMonYear(request.getMonYear());
        monthlyInput.setMonMonth(request.getMonMonth());
        monthlyInput.setMonStartDate(request.getMonStartDate());
        monthlyInput.setMonEndDate(request.getMonEndDate());
        monthlyInput.setMonContent(request.getMonContent());
        monthlyInput.setMonColor(request.getMonColor());
        return monthlyInput;
    }
}
