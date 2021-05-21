package com.ssafy.bts.Domain.Sms;

import com.ssafy.bts.Controller.Request.SmsRequset;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Sms {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int smsId;

    @Column(nullable = false)
    private String phoneNumber;

    @Column
    private String authNumber;

    @Column
    private boolean valid;

    public static Sms createSms(SmsRequset smsRequset) {
        Sms smsInput = new Sms();
        smsInput.smsId = smsRequset.getSmsId();
        smsInput.setSmsId(smsRequset.getSmsId());;
        smsInput.setPhoneNumber(smsRequset.getPhoneNumber());
        smsInput.setAuthNumber(smsRequset.getAuthNumber());
        smsInput.setValid(true);
        return smsInput;
    }
}
