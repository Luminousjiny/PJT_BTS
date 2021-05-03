package com.ssafy.bts.Controller;

import com.ssafy.bts.Controller.Request.ChangePwRequest;
import com.ssafy.bts.Controller.Request.UserRequest;
import com.ssafy.bts.Domain.User.User;
import com.ssafy.bts.Domain.User.UserDTO;
import com.ssafy.bts.Service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @ApiOperation(value = "회원가입", notes = "가입 성공시 BaseResponse에 data값으로 '성공적으로 가입' 설정 후 반환", response = BaseResponse.class)
    @PostMapping("/join")
    public BaseResponse join(@ApiParam(value = "사용자 객체") @RequestBody UserRequest request){

        BaseResponse response = null;
        try{
            User user = User.createUser(request);
            userService.save(user);
            response = new BaseResponse("success", "성공적으로 가입");
        }catch(IllegalStateException e){
            response = new BaseResponse("fail",e.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "모든 유저 목록 불러오기", notes = "모든 유저정보를 반환 List 형식으로", response = BaseResponse.class)
    @GetMapping
    public BaseResponse findUsers(){
        BaseResponse response = null;
        try{
            List<User> findUsers = userService.findAll();
            List<UserDTO> collect = findUsers.stream()
                    .map(m-> new UserDTO(m))
                    .collect(Collectors.toList());
            response = new BaseResponse("success", collect);
        }
        catch(Exception e){
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "유저 한명 찾기", notes = "모든 유저정보를 반환 List 형식으로", response = BaseResponse.class)
    @GetMapping("/{userId}")
    public BaseResponse findUser(@ApiParam(value = "사용자 로그인 아이디") @PathVariable String userId){
        BaseResponse response = null;
        try{
            User findUser = userService.findByUserId(userId);
            UserDTO userDto = new UserDTO(findUser);
            response = new BaseResponse("success", userDto);
        }
        catch(Exception e){
            response = new BaseResponse("fail",e.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "아이디 중복여부 검사", notes = "반환되는 data 값은 true / false", response = BaseResponse.class)
    @GetMapping("/check/{userId}")
    public BaseResponse duplicateUserLoginId(@ApiParam(value = "중복검사 할 아이디")@PathVariable String userId){
        BaseResponse response = null;
        try{
            boolean isCheck = userService.checkUserId(userId);
            if(isCheck){
                response = new BaseResponse("success", true);
            }else{
                response = new BaseResponse("success", false);
            }
        }catch(Exception e){
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "사용자 정보 수정(마이페이지)", notes = "반환되는 데이터는 수정 성공 / 에러 메시지", response = BaseResponse.class)
    @PutMapping("/{userId}") // 전체 수정은 put
    public BaseResponse updateUser(@ApiParam(value = "사용자 로그인 아이디")@PathVariable String userId,
                                   @ApiParam(value = "사용자 객체")@RequestBody UserRequest request) {
        BaseResponse response = null;
        try {
            userService.updateUser(userId, request);
            response = new BaseResponse("success", "수정 성공");
        } catch (IllegalStateException e) {
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "비밀번호 수정", notes = "반환되는 데이터는 수정 성공 / 에러 메시지", response = BaseResponse.class)
    @PostMapping("/pwd") // 부분 수정은 post
    public BaseResponse updatePassword(@ApiParam(value = "패스워드 수정 정보(아이디, 패스워드)")@RequestBody ChangePwRequest request) {

        BaseResponse response = null;
        try {
            userService.updatePw(request);
            response = new BaseResponse("success", "수정 성공");
        } catch (Exception e) {
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "회원 삭제(탈퇴)", notes = "반환되는 데이터는 삭제 성공 / 에러 메시지", response = BaseResponse.class)
    @DeleteMapping("/{userId}")
    public BaseResponse deleteUser(@ApiParam(value = "사용자 로그인 정보")@PathVariable String userId){
        BaseResponse response = null;
        try {
            userService.deleteUser(userId);
            response = new BaseResponse("success", "삭제 성공");
        } catch (IllegalStateException e) {
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "아이디,핸드폰 번호에 해당하는 유저 찾기(비밀번호 변경 전 확인)", notes = "찾으면 success, 못찾으면 fail", response = BaseResponse.class)
    @GetMapping("/{userId}/{userPhone}")
    public BaseResponse findUserByIdAndPhone(@ApiParam(value = "사용자 아이디") @PathVariable String userId,
                                             @ApiParam(value = "사용자 핸드폰 번호") @PathVariable String userPhone){
        BaseResponse response = null;
        try{
            User findUser = userService.findByUserIdAndUserPhone(userId, userPhone);
            if(findUser == null){
                response = new BaseResponse("fail",null);
            }else{
                response = new BaseResponse("success", findUser);
            }
        }
        catch(Exception e){
            response = new BaseResponse("fail",e.getMessage());
        }
        return response;
    }
}
