package com.ssafy.bts.Controller.Request;

import com.ssafy.bts.Domain.Room.Room;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "방명록 객체", description = "클라이언트측에서 보내주는 방명록 객체 정보")
public class GuestBookRequest {
    @ApiModelProperty(value="방명록 번호")
    private int guestId;

    @ApiModelProperty(value="방 번호")
    private Room roomId;

    @ApiModelProperty(value="유저 아이디")
    private String userId;

    @ApiModelProperty(value="방문 날짜")
    private Date visitDate;
}
