package com.ssafy.bts.Controller;

import com.ssafy.bts.Controller.Request.QnaRequest;
import com.ssafy.bts.Controller.Request.RoomRequest;
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

    @ApiOperation(value = "최초 방 생성시", notes = "유니티에서 생성된 방 번호(roomNumber)를 받아서 db에 방정보 넣고 roomId반환", response = BaseResponse.class)
    @PostMapping
    public BaseResponse createRoom(@ApiParam(value = "Room 객체", required = true) @RequestBody RoomRequest request) throws IOException {
        BaseResponse response = null;

        try {
            Room room = Room.createRoom(request);
            roomService.save(room);
            int roomId = roomService.findByRoomNumber(request.getRoomNumber()).getRoomId();
            response = new BaseResponse("success", roomId);
        } catch (IllegalStateException e) {
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "방에 가입시", notes = "유니티에서 생성된 방 번호(roomNumber) 받아서 roomId반환", response = BaseResponse.class)
    @GetMapping
    public BaseResponse joinRoom(@ApiParam(value = "Room 객체", required = true) @RequestBody RoomRequest request) throws IOException {
        BaseResponse response = null;

        try {
            int roomId = roomService.findByRoomNumber(request.getRoomNumber()).getRoomId();
            response = new BaseResponse("success", roomId);
        } catch (IllegalStateException e) {
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "방 삭제시", notes = "유니티에서 방삭제 성공시 data값으로 '삭제 성공' 설정 후 반환", response = BaseResponse.class)
    @DeleteMapping("/{roomName}")
    public BaseResponse deleteRoom(@ApiParam(value = "유니티 방번호")@PathVariable int roomNumber) throws IOException {
        BaseResponse response = null;

        try {
            int roomId = roomService.findByRoomNumber(roomNumber).getRoomId();
            roomService.deleteRoom(roomId);
            response = new BaseResponse("success", "삭제 성공");
        } catch (IllegalStateException e) {
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }


}
