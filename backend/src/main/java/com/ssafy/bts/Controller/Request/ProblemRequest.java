package com.ssafy.bts.Controller.Request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "문제 객체", description = "클라이언트측에서 보내주는 문제 객체")
public class ProblemRequest {
    @ApiModelProperty(value="방 번호")
    private int roomId;

    @ApiModelProperty(value="문제 번호")
    private int proId;

    @ApiModelProperty(value="유저 아이디")
    private String userId;

    @ApiModelProperty(value="문제 제목")
    private String proTitle;

    @ApiModelProperty(value="문제 내용")
    private String proContent;

    @ApiModelProperty(value="문제 입력")
    private String proInput;

    @ApiModelProperty(value="문제 출력")
    private String proOutput;

    @ApiModelProperty(value="문제 날짜")
    private Date proDate;
}
