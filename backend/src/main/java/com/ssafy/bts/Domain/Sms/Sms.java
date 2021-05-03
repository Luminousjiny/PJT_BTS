package com.ssafy.bts.Domain.Sms;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Sms {
    @Id
    private String phoneNumber;

    @Column
    private String authNumber;

    @Column
    private boolean valid;
}
