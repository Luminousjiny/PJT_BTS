package com.ssafy.bts.Controller.Request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "클라이언트 측에서 보내주는 User 객체 정보", description = "회원가입 시 사용된다.")
public class UserRequest {
    @ApiModelProperty(value="유저 아이디")
    private String userId;

    @ApiModelProperty(value="비밀번호")
    private String userPw;

    @ApiModelProperty(value="닉네임")
    private String userNickname;

    @ApiModelProperty(value="핸드폰번호")
    private String userPhone;
}
