package com.ssafy.bts.Domain.Monthly;

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
    private Date monStartDate;

    @Column(nullable = false)
    private Date monEndDate;

    @Column(nullable = false, columnDefinition = "text")
    private String monContent;

    @Column(nullable = false)
    private int monColor;
}
