package com.ssafy.bts.Domain.Info;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ssafy.bts.Domain.User.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter @Setter
public class Info {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int infoId;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String infoTitle;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String infoContent;

    @Column(nullable = false)
    private Date infoDate;
}
