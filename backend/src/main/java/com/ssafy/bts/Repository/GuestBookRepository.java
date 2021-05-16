package com.ssafy.bts.Repository;

import com.ssafy.bts.Domain.GuestBook.GuestBook;
import com.ssafy.bts.Domain.Room.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GuestBookRepository extends JpaRepository<GuestBook, Long> {
    @Query("select g from GuestBook g where g.room = :room order by g.visitDate desc")
    List<GuestBook> findByRoom(Room room);
}
