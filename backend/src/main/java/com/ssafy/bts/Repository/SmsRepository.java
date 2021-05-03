package com.ssafy.bts.Repository;

import com.ssafy.bts.Domain.Info.Info;
import com.ssafy.bts.Domain.Sms.Sms;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SmsRepository extends JpaRepository<Sms, Long> {
    Sms findByPhoneNumber(String phoneNumber);
}
