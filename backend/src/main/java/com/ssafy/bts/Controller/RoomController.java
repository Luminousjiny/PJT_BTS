package com.ssafy.bts.Controller;

import com.ssafy.bts.Domain.Room.Room;
import com.ssafy.bts.Service.RoomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Api
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/room")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @ApiOperation(value = "최초 방 생성시", notes = "유니티에서 생성된 학교이름을 받아서 db에 방정보 넣고 roomId반환", response = BaseResponse.class)
    @PostMapping
    public BaseResponse createRoom(@ApiParam(value = "학교이름") @RequestBody String roomName) throws IOException {
        BaseResponse response = null;

        try {
            Room room = Room.createRoom(roomName);
            roomService.save(room);
            int roomId = roomService.findByRoomName(roomName).getRoomId();
            response = new BaseResponse("success", roomId);
        } catch (IllegalStateException e) {
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "방 클릭 시", notes = "유니티에서 생성된 학교이름을 받아서 roomId반환", response = BaseResponse.class)
    @GetMapping("/{roomName}")
    public BaseResponse joinRoom(@ApiParam(value = "학교이름") @PathVariable String roomName) throws IOException {
        BaseResponse response = null;

        try {
            if(roomService.findByRoomName(roomName) == null){
                response = new BaseResponse("success", "존재하지 않는 방입니다.");
            }else{
                int roomId = roomService.findByRoomName(roomName).getRoomId();
                response = new BaseResponse("success", roomId);
            }
        } catch (IllegalStateException e) {
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }
//
//    @ApiOperation(value = "방 삭제시", notes = "유니티에서 방삭제 성공시 data값으로 '삭제 성공' 설정 후 반환", response = BaseResponse.class)
//    @DeleteMapping("/{roomName}")
//    public BaseResponse deleteRoom(@ApiParam(value = "학교이름") @PathVariable String schoolName) throws IOException {
//        BaseResponse response = null;
//
//        try {
//            int roomId = roomService.findByRoomName(schoolName).getRoomId();
//            roomService.deleteRoom(roomId);
//            response = new BaseResponse("success", "삭제 성공");
//        } catch (IllegalStateException e) {
//            response = new BaseResponse("fail", e.getMessage());
//        }
//        return response;
//    }


}
