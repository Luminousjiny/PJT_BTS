package com.ssafy.bts.Controller.Request;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "리뷰 객체", description = "클라이언트측에서 보내주는 정보 글 객체")
public class InfoRequest {
    private String userId;
    private String infoTitle;
    private String infoContent;
}
