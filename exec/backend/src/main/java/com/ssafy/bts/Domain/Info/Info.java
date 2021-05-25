package com.ssafy.bts.Domain.Info;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ssafy.bts.Controller.Request.InfoRequest;
import com.ssafy.bts.Domain.Room.Room;
import com.ssafy.bts.Domain.User.User;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;

@Entity
@Getter @Setter
public class Info {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int infoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String infoTitle;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String infoContent;

    @Column(nullable = false)
    private Date infoDate;

    public static Info createInfo(InfoRequest request){
        Info infoInput = new Info();
        infoInput.setInfoTitle(request.getInfoTitle());
        infoInput.setInfoContent(request.getInfoContent());
        return infoInput;
    }

    @PrePersist
    private void onCreate() { this.infoDate = new Date(); }
}
