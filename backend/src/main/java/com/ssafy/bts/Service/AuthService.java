package com.ssafy.bts.Service;

import com.ssafy.bts.Controller.Request.SmsVaildRequest;
import com.ssafy.bts.Domain.Sms.Sms;
import com.ssafy.bts.Repository.SmsRepository;
import lombok.RequiredArgsConstructor;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final SmsRepository smsRepository;

    /**
     * 입력된 핸드폰 번호로 인증번호 리스트 불러오기
     * @param phoneNumber
     */
    @Transactional
    public List<Sms> findByPhoneNumber(String phoneNumber) {
        return smsRepository.findByPhoneNumber(phoneNumber);
    }

    /**
     * 상태 false로 바꾸기
     */
    @Transactional
    public void updateValid(SmsVaildRequest requset) {
        Optional<List<Sms>> findSms = Optional.ofNullable(smsRepository.findByPhoneNumber(requset.getPhoneNumber()));
        if(findSms.isPresent()){
            for (int i = 0; i < findSms.get().size(); i++) {
                findSms.get().get(i).setValid(false);
            }
        }else{
            throw new IllegalStateException("잘못된 핸드폰 번호입니다.");
        }
    }

    /**
     * 새로운 인증번호 저장 + true 처리
     */
    @Transactional
    public Sms save(Sms sms) {
        return smsRepository.save(sms);
    }

    /**
     * 입력 인증번호와 테이블의 true 인증번호가 같은지
     * => 같으면 true , 다르면 false
     */
    @Transactional
    public boolean checkNum(String smsCheckNum) {
        Sms sms = smsRepository.findByAuthNumber(smsCheckNum);
        if (sms != null){ // 동일한 인증번호 존재
            if(sms.isValid()){
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }

    /**
     * 문자보내기
     */
    @Transactional
    public void sendSMS(String phoneNumber, String secret) throws CoolsmsException {
        String api_key = "NCSFQBRFUJJUMQZP";
        String api_secret = "VO8AY771ZW6VSAD2JWJMD6N2JYF1K1LV";

        Message SMS = new Message(api_key, api_secret);
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("to", phoneNumber);
        params.put("from", "01071231815"); //무조건 자기번호 (인증)
        params.put("type", "SMS");
        params.put("text", "Sera 휴대폰 인증입니다.\n인증번호는 "+"["+ secret+"]" + "입니다.");
        params.put("app_version", "test app 1.2"); // application name and version
        SMS.send(params);
    }
}
