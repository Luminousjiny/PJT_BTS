package com.ssafy.bts.Repository;

import com.ssafy.bts.Domain.GuestBook.GuestBook;
import com.ssafy.bts.Domain.Room.Room;
import com.ssafy.bts.Domain.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GuestBookRepository extends JpaRepository<GuestBook, Long> {
    @Query("select g from GuestBook g where g.room = :room order by g.attendDate desc")
    List<GuestBook> findByRoom(Room room);

    @Query("select g from GuestBook g where g.room = :room and g.user = :user")
    GuestBook findByRoomAndUser(Room room, User user);
}
