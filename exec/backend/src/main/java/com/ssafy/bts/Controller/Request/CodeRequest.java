package com.ssafy.bts.Controller.Request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "코드 객체", description = "클라이언트측에서 보내주는 코드 객체")
public class CodeRequest {
    @ApiModelProperty(value="방 번호")
    private int roomId;

    @ApiModelProperty(value="코드 번호")
    private int codeId;

    @ApiModelProperty(value="문제 번호")
    private int proId;

    @ApiModelProperty(value="유저 아이디")
    private String userId;

    @ApiModelProperty(value="코드 내용")
    private String codeContent;

    @ApiModelProperty(value="코드 메모리")
    private String codeMemory;

    @ApiModelProperty(value="코드 시간")
    private String codeTime;

    @ApiModelProperty(value="코드 언어")
    private String codeLan;

    @ApiModelProperty(value="코드 날짜")
    private Date codeDate;

    @ApiModelProperty(value="코드 제출시 성공 실패 여부")
    private String result;
}
