package com.ssafy.bts.Domain.GuestBook;


import com.ssafy.bts.Domain.Room.RoomDTO;
import com.ssafy.bts.Domain.User.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GuestBookDTO {
    private int guestId;
    private RoomDTO room;
    private UserDTO user;
    private String attendDate;
    private String finishDate;

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
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        if(guestBook.getAttendDate() != null){
            cal.setTime(guestBook.getAttendDate());
            cal.add(Calendar.HOUR, +9);
            this.attendDate = df.format(cal.getTime());
        }

        if(guestBook.getFinishDate() != null){
            cal.setTime(guestBook.getFinishDate());
            cal.add(Calendar.HOUR, +9);
            this.finishDate = df.format(cal.getTime());
        }
    }
}
