package com.ssafy.bts.Controller.Request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;

@Data
@ApiModel(value = "주간 플랜 객체", description = "클라이언트측에서 보내주는 주간 플랜 객체")
public class WeeklyRequest {
    @ApiModelProperty(value="주간 번호")
    private int weekId;

    @ApiModelProperty(value="로그인한 아이디")
    private String userId;

    @ApiModelProperty(value="플랜 작성 날짜(0000-00-00)")
    private Date weekDate;

    @ApiModelProperty(value="플랜 시작 시간(00:00:00)")
    private Date weekStartTime;

    @ApiModelProperty(value="플랜 끝나는 시간(00:00:00)")
    private Date weekEndTime;

    @ApiModelProperty(value="플랜 내용")
    private String weekContent;
}
