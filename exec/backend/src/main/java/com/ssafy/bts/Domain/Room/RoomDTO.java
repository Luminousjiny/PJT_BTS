package com.ssafy.bts.Domain.Room;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomDTO {
    private int roomId;
    private String roomName;

    public RoomDTO(Room room){
        this.roomId = room.getRoomId();
        this.roomName = room.getRoomName();
    }
}
