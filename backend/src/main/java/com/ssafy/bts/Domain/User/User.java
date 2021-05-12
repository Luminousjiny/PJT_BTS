package com.ssafy.bts.Domain.User;

import com.ssafy.bts.Controller.Request.UserRequest;
import com.ssafy.bts.Domain.Coding.Code;
import com.ssafy.bts.Domain.Coding.Problem;
import com.ssafy.bts.Domain.Coding.Solve;
import com.ssafy.bts.Domain.Comment.Comment;
import com.ssafy.bts.Domain.Info.Info;
import com.ssafy.bts.Domain.Monthly.Monthly;
import com.ssafy.bts.Domain.Qna.Qna;
import com.ssafy.bts.Domain.Room.Room;
import com.ssafy.bts.Domain.Weekly.Weekly;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter
@ApiModel(value = "사용자 엔티티", description = "사용자에 대한 테이블과 1대1로 매핑이되는 데이터이다.")
public class User { // DB에 매핑용
    @Id
    private String userId;

    @Column(nullable = false)
    private String userPw;

    @Column(nullable = false)
    private String userNickname;

    @Column(nullable = false)
    private String userPhone;

    @Column(columnDefinition = "TEXT")
    private String userImg;

    @Column
    private String userLank;

    @Column
    private int userPoint;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<Qna> qna = new HashSet<>();

    @OneToMany(mappedBy = "qna", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<Comment> comment = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<Info> info = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<Problem> problems = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<Code> codes = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<Solve> solves = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<Monthly> monthly = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<Weekly> weekly = new HashSet<>();

    public static User createUser(UserRequest request) {
        User userInput = new User();
        userInput.userId = request.getUserId();
        userInput.setUserId(request.getUserId());
        userInput.setUserPw(request.getUserPw());
        userInput.setUserNickname(request.getUserNickname());
        userInput.setUserPhone(request.getUserPhone());
        userInput.setUserImg(null);
        userInput.setUserLank("브론즈3");
        userInput.setUserPoint(0);
        return userInput;
    }
}
