package com.ssafy.bts.Service;

import com.ssafy.bts.Domain.Sms.Sms;
import com.ssafy.bts.Repository.SmsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private SmsRepository smsRepository;

    @Transactional
    public Sms findByUserPhone(String phoneNumber) {
        return smsRepository.findByPhoneNumber(phoneNumber);
    }
}
