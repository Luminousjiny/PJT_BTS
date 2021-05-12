package com.ssafy.bts.Domain.GuestBook;

import com.ssafy.bts.Domain.Room.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GuestBookDTO {
    private int guestId;
    private Room room;
    private String userId;
    private Date visitDate;

    public GuestBookDTO(GuestBook guestBook){
        this.guestId = guestBook.getGuestId();

        if(guestBook.getRoom() != null){
            room = new Room();
            this.room.setRoomId(guestBook.getRoom().getRoomId());
        }

        this.userId = guestBook.getUserId();
        this.visitDate = guestBook.getVisitDate();
    }
}
