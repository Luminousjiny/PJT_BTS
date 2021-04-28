package com.ssafy.bts.Domain.Qna;

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
    private Integer qnaId;

//    private User user;

    @Column(nullable = false)
    private String qnaTitle;

    @Column(nullable = false)
    private String qnaContent;

    @Column(nullable = false)
    private Date qnaDate;
}
