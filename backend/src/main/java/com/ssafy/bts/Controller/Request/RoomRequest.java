package com.ssafy.bts.Controller.Request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Room 객체", description = "유니티에서 보내주는 Room 객체")
public class RoomRequest {
    @ApiModelProperty(value="방 번호")
    private int roomNumber;

    @ApiModelProperty(value="방 이름")
    private String roomName;
}
