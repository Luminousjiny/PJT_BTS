package com.ssafy.bts.Domain.GuestBook;

import com.ssafy.bts.Domain.Room.Room;
import com.ssafy.bts.Domain.User.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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

    @Column(nullable = false)
    private Date finishDate;

    public static GuestBook createGuestBook(){
        GuestBook inputGuestBook = new GuestBook();
        return inputGuestBook;
    }

    @PrePersist
    private void onCreate() {
        this.attendDate = new Date();
    }
}
