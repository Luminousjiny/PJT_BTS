package com.ssafy.bts.Domain.Coding;

import com.ssafy.bts.Domain.Coding.Problem;
import com.ssafy.bts.Domain.User.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Code implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pro_id")
    private Problem problem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String codeContent;

    @Column(nullable = false)
    private String codeMemory;

    @Column(nullable = false)
    private String codeTime;

    @Column(nullable = false)
    private String codeLan;

    @Column(nullable = false)
    private Date codeDate;

}
