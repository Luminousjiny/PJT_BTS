package com.ssafy.bts.Controller.Request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "시작날짜, 종료날짜 객체", description = "클라이언트측에서 플랜 조회할때 보내주는 시작날짜, 종료날짜 객체")
public class SelectWeeklyRequest {
    @ApiModelProperty(value="주간 시작 날짜('00-00-00')")
    private Date startDate;

    @ApiModelProperty(value="주간 종료 날짜('00-00-00')")
    private Date endDate;
}
