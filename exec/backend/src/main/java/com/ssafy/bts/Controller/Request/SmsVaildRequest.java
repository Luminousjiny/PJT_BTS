package com.ssafy.bts.Controller.Request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "새로운 sms 요청 시 기존 인증번호 처리할 때 사용", description = "핸드폰 번호, 유효여부")
public class SmsVaildRequest {
    @ApiModelProperty(value="핸드폰 번호")
    private String phoneNumber;

    @ApiModelProperty(value="인증했는지")
    private boolean valid;
}
