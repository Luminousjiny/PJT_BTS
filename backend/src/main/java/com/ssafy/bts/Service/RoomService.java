package com.ssafy.bts.Service;

import com.ssafy.bts.Controller.Request.RoomRequest;
import com.ssafy.bts.Domain.Qna.Qna;
import com.ssafy.bts.Domain.Room.Room;
import com.ssafy.bts.Repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.Optional;

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

    /**
     * 방 생성
     *
     * @return
     */
    @Transient
    public Room save(Room room) {
        return roomRepository.save(room);
    }

    /**
     * 방 검색
     *
     * @return
     */
    public Room findByRoomNumber(int roomNumber) {
        return roomRepository.findByRoomNumber(roomNumber);
    }

    /**
     * 방 삭제
     *
     * @return
     */
    public void deleteRoom(int roomId) {
        Optional<Room> deleteRoom = Optional.ofNullable(roomRepository.findByRoomId(roomId));
        if(deleteRoom.isPresent()){
            roomRepository.delete(deleteRoom.get());
        }else{
            throw new IllegalStateException("존재하지 않는 방 입니다.");
        }
    }
}
