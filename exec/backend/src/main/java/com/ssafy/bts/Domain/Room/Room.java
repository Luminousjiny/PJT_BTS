package com.ssafy.bts.Domain.Room;

import com.ssafy.bts.Domain.Coding.Code;
import com.ssafy.bts.Domain.Coding.Problem;
import com.ssafy.bts.Domain.Coding.Solve;
import com.ssafy.bts.Domain.Comment.Comment;
import com.ssafy.bts.Domain.Info.Info;
import com.ssafy.bts.Domain.Qna.Qna;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roomId;

    @Column(nullable = false)
    private String roomName;

    @OneToMany(mappedBy = "room", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<Qna> qna = new HashSet<>();

    @OneToMany(mappedBy = "room", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<Comment> comment = new HashSet<>();

    @OneToMany(mappedBy = "room", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<Info> info = new HashSet<>();

    @OneToMany(mappedBy = "room", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<Problem> problems = new HashSet<>();

    @OneToMany(mappedBy = "room", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<Code> codes = new HashSet<>();

    @OneToMany(mappedBy = "room", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<Solve> solves = new HashSet<>();

    public static Room createRoom(String schoolName) {
        Room roomInput = new Room();
        roomInput.setRoomName(schoolName);
        return roomInput;
    }
}
