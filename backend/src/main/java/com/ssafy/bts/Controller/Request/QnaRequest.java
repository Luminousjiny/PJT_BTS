package com.ssafy.bts.Controller.Request;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "Qna 객체", description = "클라이언트측에서 보내주는 qna 글 객체")
public class QnaRequest {
    private int qnaId;
    private String userId;
    private String qnaTitle;
    private String qnaContent;
}
