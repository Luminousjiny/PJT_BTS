package com.ssafy.bts.Domain.Room;

import com.ssafy.bts.Controller.Request.RoomRequest;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roomId;

    @Column(nullable = false)
    private int roomNumber;

    @Column(nullable = false)
    private String roomName;

    public static Room createRoom(RoomRequest roomRequest) {
        Room roomInput = new Room();
        roomInput.setRoomNumber(roomRequest.getRoomNumber());
        roomInput.setRoomName(roomRequest.getRoomName());
        return roomInput;
    }
}
