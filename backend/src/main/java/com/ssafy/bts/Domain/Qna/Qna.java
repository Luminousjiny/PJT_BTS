package com.ssafy.bts.Domain.Qna;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ssafy.bts.Controller.Request.QnaRequest;
import com.ssafy.bts.Domain.Room.Room;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;


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

    public static Qna createQna(QnaRequest request) {
        Qna qnaInput = new Qna();
        qnaInput.setQnaTitle(request.getQnaTitle());
        qnaInput.setQnaContent(request.getQnaContent());
        return qnaInput;
    }

    @PrePersist // manager persist 의해 처음 호출될 때 실행
    private void onCreate() {
        this.qnaDate = new Date();
    }

}
