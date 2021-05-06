package com.ssafy.bts.Domain.Comment;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ssafy.bts.Controller.Request.CommentRequest;
import com.ssafy.bts.Domain.Qna.Qna;
import com.ssafy.bts.Domain.User.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter @Setter
public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int comId;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "qna_id")
    private Qna qna;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String comContent;

    @Column(nullable = false)
    private Date comDate;

    public static Comment createComment(CommentRequest request) {
        Comment comInput = new Comment();
        comInput.setComContent(request.getComContent());
        return comInput;
    }

    @PrePersist // manager persist 의해 처음 호출될 때 실행
    private void onCreate() {
        this.comDate = new Date();
    }
}
