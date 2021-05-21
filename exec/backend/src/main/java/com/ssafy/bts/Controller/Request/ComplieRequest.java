package com.ssafy.bts.Controller.Request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "컴파일 객체", description = "클라이언트측에서 보내주는 컴파일 객체")
public class ComplieRequest {
    @ApiModelProperty(value="컴파일 번호")
    private int compilerId;

    @ApiModelProperty(value="소스코드")
    private String source;

    @ApiModelProperty(value="입력값")
    private String input;
}
