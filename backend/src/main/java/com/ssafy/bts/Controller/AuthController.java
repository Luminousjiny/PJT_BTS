package com.ssafy.bts.Controller;

import com.ssafy.bts.Domain.Sms.Sms;
import com.ssafy.bts.Domain.Sms.SmsDTO;
import com.ssafy.bts.Service.AuthService;
import com.ssafy.bts.Service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@Api
@RestController
@RequestMapping("v1/auth")
@CrossOrigin(origins = {"*"})
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    @ApiOperation(value = "인증번호 요청", notes = "마이페이지 / 회원가입 SMS 요청",response = BaseResponse.class)
    @PostMapping
    public BaseResponse postAuthNumber (@ApiParam(value = "휴대폰 번호") @RequestParam(required = false) String phoneNumber){
        BaseResponse response = null;
        try{
            Random random = new Random();
            String secret = "";
            for(int i= 0 ; i < 6; i++){
                secret += Integer.toString(random.nextInt(10));
            }
            Sms findSms = authService.findByUserPhone(phoneNumber); // 핸드폰번호로 데이터 찾기
            SmsDTO smsDto = new SmsDTO(findSms);
            System.out.println(smsDto);

            response = new BaseResponse("success", "true");
        }catch(Exception e){
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

}
