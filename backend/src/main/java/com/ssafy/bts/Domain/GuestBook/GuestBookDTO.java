package com.ssafy.bts.Domain.GuestBook;

import com.ssafy.bts.Domain.Room.Room;
import com.ssafy.bts.Domain.Room.RoomDTO;
import com.ssafy.bts.Domain.User.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sun.java2d.pipe.SpanShapeRenderer;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GuestBookDTO {
    private int guestId;
    private RoomDTO room;
    private UserDTO user;
    private String visitDate;

    public GuestBookDTO(GuestBook guestBook) {
        this.guestId = guestBook.getGuestId();

        if(guestBook.getRoom() != null){
            room = new RoomDTO();
            this.room.setRoomId(guestBook.getRoom().getRoomId());
        }

        if(guestBook.getUser() != null){
            user = new UserDTO();
            this.user.setUserId(guestBook.getUser().getUserId());
            this.user.setUserNickname(guestBook.getUser().getUserNickname());
        }
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(guestBook.getVisitDate());
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.visitDate = df.format(cal.getTime());
    }
}
