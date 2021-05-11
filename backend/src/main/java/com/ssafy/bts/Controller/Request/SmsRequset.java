package com.ssafy.bts.Controller.Request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "새로운 유효번호 추가시 사용", description = "핸드폰 번호, 유효번호, 유효여부")
public class SmsRequset {
    @ApiModelProperty(value="SMS 번호")
    private int smsId;

    @ApiModelProperty(value="핸드폰 번호")
    private String phoneNumber;

    @ApiModelProperty(value="인증 번호")
    private String authNumber;

    @ApiModelProperty(value="유효한지")
    private boolean valid;
}
