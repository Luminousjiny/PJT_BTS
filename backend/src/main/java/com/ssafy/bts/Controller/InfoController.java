package com.ssafy.bts.Controller;

import com.ssafy.bts.Controller.Request.InfoRequest;
import com.ssafy.bts.Domain.Info.Info;
import com.ssafy.bts.Domain.User.User;
import com.ssafy.bts.Service.InfoService;
import com.ssafy.bts.Service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Api
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/info")
@RequiredArgsConstructor
public class InfoController {
    private final InfoService infoService;
    private final UserService userService;

    @ApiOperation(value = "정보 글작성", notes = "정보 글 작성 성공시 BaseResponse에 data값으로 '성공적으로 작성' 설정 후 반환", response = BaseResponse.class)
    @PostMapping
    public BaseResponse writeInfo(@ApiParam(value = "info 객체", required=true) @RequestBody InfoRequest request) throws IOException {
        BaseResponse response = null;

        try{
            Info info = Info.createInfo(request);
            User user = userService.findByUserId(request.getUserId());
            info.setUser(user);
            infoService.save(info);
            response = new BaseResponse("success", "성공적으로 작성");
        }catch(IllegalStateException e){
            response = new BaseResponse("fail",e.getMessage());
        }
        return response;
    }

}
