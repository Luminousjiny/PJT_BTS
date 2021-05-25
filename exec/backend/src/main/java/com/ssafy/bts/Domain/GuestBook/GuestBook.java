package com.ssafy.bts.Domain.GuestBook;

import com.ssafy.bts.Domain.Room.Room;
import com.ssafy.bts.Domain.User.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Entity
@Getter
@Setter
public class GuestBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int guestId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private Date attendDate;

    @Column(nullable = true)
    private Date finishDate;

    public static GuestBook createGuestBook(){
        GuestBook inputGuestBook = new GuestBook();
        return inputGuestBook;
    }

    @PrePersist
    private void onCreate() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();

        cal.setTime(new Date());
        cal.add(Calendar.HOUR, +9);
        String attendString = sdf.format(cal.getTime());
        this.attendDate = sdf.parse(attendString);//db 등교시간
    }
}
