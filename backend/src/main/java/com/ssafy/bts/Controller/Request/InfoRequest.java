package com.ssafy.bts.Controller.Request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "정보 객체", description = "클라이언트측에서 보내주는 정보 글 객체")
public class InfoRequest {
    @ApiModelProperty(value="방 번호")
    private int roomId;

    @ApiModelProperty(value="정보글 번호")
    private int infoId;

    @ApiModelProperty(value="로그인한 아이디")
    private String userId;

    @ApiModelProperty(value="정보글 제목")
    private String infoTitle;

    @ApiModelProperty(value="정보글 내용")
    private String infoContent;
}
