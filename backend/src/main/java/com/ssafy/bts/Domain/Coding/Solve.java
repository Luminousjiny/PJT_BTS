package com.ssafy.bts.Domain.Coding;

import com.ssafy.bts.Controller.Request.SolveRequest;
import com.ssafy.bts.Domain.User.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Solve {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int solveId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pro_id")
    private Problem problem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}
