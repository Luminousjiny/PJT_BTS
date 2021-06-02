package com.ssafy.bts.Domain.Coding;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ssafy.bts.Controller.Request.ProblemRequest;
import com.ssafy.bts.Domain.Room.Room;
import com.ssafy.bts.Domain.User.User;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Getter @Setter
public class Problem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int proId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String proTitle;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String proContent;

    @Column(nullable = false)
    private String proInput;

    @Column(nullable = false)
    private String proOutput;

    @Column(nullable = false)
    private Date proDate;

    @JsonBackReference
    @OneToMany(mappedBy="problem", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Code> codes = new ArrayList<>();

    @JsonBackReference
    @OneToMany(mappedBy="problem", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Solve> solve = new ArrayList<>();

    public static Problem createProblem(ProblemRequest request) {
        Problem pInput = new Problem();
        pInput.setProTitle(request.getProTitle());
        pInput.setProContent(request.getProContent());
        pInput.setProInput(request.getProInput());
        pInput.setProOutput(request.getProOutput());
        return pInput;
    }

    @PrePersist
    private void onCreate() {
        this.proDate = new Date();
    }
}
