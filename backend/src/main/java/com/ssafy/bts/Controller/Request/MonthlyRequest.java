package com.ssafy.bts.Controller.Request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "월간 플랜 객체", description = "클라이언트측에서 보내주는 월간 플랜 객체")
public class MonthlyRequest {
    @ApiModelProperty(value="플랜 번호")
    private int monId;

    @ApiModelProperty(value="로그인한 아이디")
    private String userId;

    @ApiModelProperty(value="년도")
    private int monYear;

    @ApiModelProperty(value="월")
    private int monMonth;

    @ApiModelProperty(value="시작 날짜")
    private Date monStartDate;

    @ApiModelProperty(value="끝나는 날짜")
    private Date monEndDate;

    @ApiModelProperty(value="플랜 내용")
    private String monContent;

    @ApiModelProperty(value="플랜 색상(0~6)")
    private int monColor;
}
