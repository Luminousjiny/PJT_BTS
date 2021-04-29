package com.ssafy.bts.Domain.Coding;

import com.ssafy.bts.Domain.User.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter @Setter
public class Problem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int proId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String proTitle;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String proContent;

    @Column(nullable = false)
    private Date proDate;
}
