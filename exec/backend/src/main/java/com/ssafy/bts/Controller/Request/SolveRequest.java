package com.ssafy.bts.Controller.Request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "푼 문제 관리하는 객체", description = "클라이언트측에서 보내주는 객체")
public class SolveRequest {
    @ApiModelProperty(value="푼 문제 번호")
    private int proId;

    @ApiModelProperty(value="유저 아이디")
    private String userId;
}
