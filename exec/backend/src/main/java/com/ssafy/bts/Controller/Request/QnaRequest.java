package com.ssafy.bts.Controller.Request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Qna 객체", description = "클라이언트측에서 보내주는 qna 글 객체")
public class QnaRequest {
    @ApiModelProperty(value="방 번호")
    private int roomId;

    @ApiModelProperty(value="질문 번호")
    private int qnaId;

    @ApiModelProperty(value="유저 아이디")
    private String userId;

    @ApiModelProperty(value="질문 제목")
    private String qnaTitle;

    @ApiModelProperty(value="질문 내용")
    private String qnaContent;
}
