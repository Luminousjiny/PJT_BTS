package com.ssafy.bts.Domain.Qna;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ssafy.bts.Domain.User.User;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter @Setter
public class Qna {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 생성을 db에 위임
    private int qnaId;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String qnaTitle;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String qnaContent;

    @Column(nullable = false)
    private Date qnaDate;
}
