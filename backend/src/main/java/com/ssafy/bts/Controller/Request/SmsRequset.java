package com.ssafy.bts.Controller.Request;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "새로운 유효번호 추가시 사용", description = "핸드폰 번호, 유효번호, 유효여부")
public class SmsRequset {
    private int smsId;
    private String phoneNumber;
    private String authNumber;
    private boolean valid;
}
