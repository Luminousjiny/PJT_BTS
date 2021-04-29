package com.ssafy.bts.Domain.User;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@ApiModel(value = "사용자 entity", description = "사용자에 대한 테이블과 1대1로 매핑이되는 데이터이다.")
public class User { // DB에 매핑용
    @Id
    private String userId;

    @Column(nullable = false)
    private String userPw;

    @Column(nullable = false)
    private String userNickname;

    @Column(nullable = false)
    private int userPhone;

    @Column(columnDefinition = "TEXT")
    private String userImg;

    @Column(nullable = false)
    private String userLank;

    @Column(nullable = false)
    private int userPoint;

}
