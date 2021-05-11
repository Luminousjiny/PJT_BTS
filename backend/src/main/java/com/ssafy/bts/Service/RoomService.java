package com.ssafy.bts.Service;

import com.ssafy.bts.Domain.Room.Room;
import com.ssafy.bts.Repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.beans.Transient;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;

    /**
     * 현재 방 객체 찾기
     *
     * @return
     */
    @Transient
    public Room findByRoomId(int roomId) { return roomRepository.findByRoomId(roomId);}
}
