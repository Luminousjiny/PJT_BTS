package com.ssafy.bts.Controller.Request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "댓글 객체", description = "클라이언트측에서 보내주는 댓글 객체 정보")
public class CommentRequest {
    @ApiModelProperty(value="방 번호")
    private int roomId;

    @ApiModelProperty(value="댓글 번호")
    private int comId;

    @ApiModelProperty(value="글 번호")
    private int qnaId;

    @ApiModelProperty(value="유저 아이디")
    private String userId;

    @ApiModelProperty(value="댓글 내용")
    private String comContent;
}
