package com.ssafy.bts.Domain.Sms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SmsDTO {
    private String phoneNumber;
    private String authNumber;
    private boolean valid;

    public SmsDTO(Sms sms) {
        this.phoneNumber = sms.getPhoneNumber();
        this.authNumber = sms.getAuthNumber();
        this.valid = sms.isValid();
    }
}
