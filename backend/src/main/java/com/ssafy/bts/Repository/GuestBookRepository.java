package com.ssafy.bts.Repository;

import com.ssafy.bts.Domain.GuestBook.GuestBook;
import com.ssafy.bts.Domain.Room.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GuestBookRepository extends JpaRepository<GuestBook, Long> {
    List<GuestBook> findByRoom(Room room);
}
