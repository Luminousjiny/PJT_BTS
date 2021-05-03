package com.ssafy.bts.Controller.Request;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "클라이언트 측에서 보내주는 User 객체 정보", description = "회원가입 시 사용된다.")
public class UserRequest {
    private String userId;
    private String userPw;
    private String userNickname;
    private String userPhone;
}
