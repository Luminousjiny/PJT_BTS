package com.ssafy.bts.Domain.User;

import com.ssafy.bts.Controller.Request.UserRequest;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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
