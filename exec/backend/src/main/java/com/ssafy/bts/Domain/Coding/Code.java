package com.ssafy.bts.Domain.Coding;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ssafy.bts.Controller.Request.CodeRequest;
import com.ssafy.bts.Domain.Room.Room;
import com.ssafy.bts.Domain.User.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Code implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codeId;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pro_id")
    private Problem problem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String codeContent;

    @Column(nullable = false)
    private String codeMemory;

    @Column(nullable = false)
    private String codeTime;

    @Column(nullable = false)
    private String codeLan;

    @Column(nullable = false)
    private Date codeDate;

    @Column(nullable=false)
    private String codeResult;

    public static Code createCode(CodeRequest request) {
        Code inputCode = new Code();
        inputCode.setCodeContent(request.getCodeContent());
        inputCode.setCodeLan(request.getCodeLan());
        inputCode.setCodeMemory(request.getCodeMemory());
        inputCode.setCodeTime(request.getCodeTime());
        inputCode.setCodeResult(request.getResult());
        return inputCode;
    }

    @PrePersist
    private void onCreate() { this.codeDate = new Date(); }
}
