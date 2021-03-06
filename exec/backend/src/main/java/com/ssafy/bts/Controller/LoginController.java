package com.ssafy.bts.Controller;

import com.ssafy.bts.Controller.Request.UserRequest;
import com.ssafy.bts.Domain.User.User;
import com.ssafy.bts.Service.JwtService;
import com.ssafy.bts.Service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Api
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/login")
@RequiredArgsConstructor
public class LoginController {
    private final JwtService jwtService;
    private final UserService userService;

    public static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @ApiOperation(value = "로그인", notes = "토큰이 유요한가, 로그인이 되는가에 대한 데이터가 반환됨",response = ResponseEntity.class)
    @PostMapping
    public ResponseEntity<Map<String, Object>> login(@ApiParam(value = "사용자 객체") @RequestBody UserRequest userRequest,
                                                     HttpServletResponse response,
                                                     HttpSession session){
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        try{
            User loginUser = userService.findByUserIdAndUserPw(userRequest.getUserId(), userRequest.getUserPw());
            if(loginUser!=null){
                User userInfo = userService.findByUserId(userRequest.getUserId());
                String token = jwtService.create(loginUser);
                logger.trace("로그인 토큰정보 : {}", token);
                response.setHeader("auth_token", token);
                resultMap.put("auth_token",token);
                resultMap.put("status", true);
                resultMap.put("user", userInfo);
                resultMap.put("message", "login success");
            }
            else{
                loginUser = userService.findByUserId(userRequest.getUserId());
                if(loginUser != null){
                    resultMap.put("message", "로그인 실패 : 비밀번호가 틀렸습니다.");
                }else{
                    resultMap.put("message", "로그인 실패 : 존재하지 않는 아이디입니다.");
                }
            }
            status = HttpStatus.ACCEPTED;
        }catch(Exception e){
            logger.error("로그인 실패 : {}", e);
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }
}
