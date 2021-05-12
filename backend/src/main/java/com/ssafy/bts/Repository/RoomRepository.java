package com.ssafy.bts.Repository;

import com.ssafy.bts.Domain.Room.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
    Room findByRoomId(int roomId);
    Room findByRoomNumber(int roomNumber);
}
