package com.ssafy.bts.Domain.Room;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomDTO {
    private int roomId;
    private int roomNumber;
    private String roomName;

    public RoomDTO(Room room){
        this.roomId = room.getRoomId();
        this.roomNumber = room.getRoomNumber();
        this.roomName = room.getRoomName();
    }
}
