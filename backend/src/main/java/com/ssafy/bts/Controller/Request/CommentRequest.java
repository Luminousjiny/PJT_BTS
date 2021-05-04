package com.ssafy.bts.Controller.Request;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "댓글 객체", description = "클라이언트측에서 보내주는 댓글 객체 정보")
public class CommentRequest {
    private int comId;
    private int qnaId;
    private String userId;
    private String comContent;
}
