package com.ssafy.bts.Repository;

import com.ssafy.bts.Domain.Sms.Sms;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SmsRepository extends JpaRepository<Sms, Long> {
    List<Sms> findByPhoneNumber(String phoneNumber);
    Sms findByAuthNumber(String smsCheckNum);
}
