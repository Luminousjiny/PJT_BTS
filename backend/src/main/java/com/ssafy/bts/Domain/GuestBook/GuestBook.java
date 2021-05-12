package com.ssafy.bts.Domain.GuestBook;

import com.ssafy.bts.Controller.Request.GuestBookRequest;
import com.ssafy.bts.Domain.Room.Room;
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

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private Date visitDate;

    public static GuestBook createGuestBook(GuestBookRequest request){
        GuestBook inputGuestBook = new GuestBook();
        inputGuestBook.setUserId(request.getUserId());
        return inputGuestBook;
    }

    @PrePersist
    private void onCreate() { this.visitDate = new Date(); }
}
