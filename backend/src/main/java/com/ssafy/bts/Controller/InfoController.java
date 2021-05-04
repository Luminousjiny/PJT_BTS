package com.ssafy.bts.Controller;

import com.ssafy.bts.Controller.Request.InfoRequest;
import com.ssafy.bts.Domain.Info.Info;
import com.ssafy.bts.Domain.Info.InfoDTO;
import com.ssafy.bts.Domain.User.User;
import com.ssafy.bts.Service.InfoService;
import com.ssafy.bts.Service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Api
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/info")
@RequiredArgsConstructor
public class InfoController {
    private final InfoService infoService;
    private final UserService userService;

    @ApiOperation(value = "정보 글작성", notes = "글 작성 성공시 data값으로 '작성 성공' 설정 후 반환", response = BaseResponse.class)
    @PostMapping
    public BaseResponse writeInfo(@ApiParam(value = "info 객체", required=true) @RequestBody InfoRequest request) throws IOException {
        BaseResponse response = null;

        try{
            Info info = Info.createInfo(request);
            User user = userService.findByUserId(request.getUserId());
            info.setUser(user);
            infoService.save(info);
            response = new BaseResponse("success", "작성 성공");
        }catch(IllegalStateException e){
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "정보 글수정", notes = "글 수정 성공시 data값으로 '수정 성공' 설정 후 반환", response = BaseResponse.class)
    @PutMapping
    public BaseResponse updateInfo(@ApiParam(value = "리뷰 객체", required=false) @RequestBody InfoRequest request) {
        BaseResponse response = null;
        try {
            infoService.updateInfo(request);
            response = new BaseResponse("success", "수정 성공");
        } catch (IllegalStateException | IOException e) {
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "정보 글삭제", notes = "글 삭제 성공시 data값으로 '삭제 성공' 설정 후 반환", response = BaseResponse.class)
    @DeleteMapping("/{infoId}")
    public BaseResponse deleteInfo(@ApiParam(value = "글 아이디")@PathVariable int infoId) {
        BaseResponse response = null;
        try {
            infoService.deleteInfo(infoId);
            response = new BaseResponse("success", "삭제 성공");
        } catch (IllegalStateException e) {
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "정보 글 전체 리스트 조회", notes = "List 형식으로 반환", response = BaseResponse.class)
    @GetMapping("/list")
    public BaseResponse findAllInfo(){
        BaseResponse response = null;
        try{
            List<Info> reviewList  = infoService.findAll();
            List<InfoDTO> collect = reviewList.stream()
                    .map(m-> new InfoDTO(m))
                    .collect(Collectors.toList());
            response = new BaseResponse("success", collect);
        }
        catch(Exception e){
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "현재 글 상세 보여주기", notes = "InfoDto 형식으로 반환", response = BaseResponse.class)
    @GetMapping("/detail/{infoId}")
    public BaseResponse detailInfo(@ApiParam(value = "글 아이디")@PathVariable int infoId){
        BaseResponse response = null;
        try{
            Info info = infoService.findByInfoId(infoId);
            InfoDTO infoDTO = new InfoDTO(info);
            response = new BaseResponse("success", infoDTO);
        }catch(IllegalStateException e){
            response = new BaseResponse("fail",e.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "제목으로 글 검색", notes = "검색 결과 있으면 data값으로 List 형식으로 반환 / 없으면 null반환", response = BaseResponse.class)
    @GetMapping("/searchTitle/{keyword}")
    public BaseResponse searchByTitle(@ApiParam(value = "제목 검색 키워드")@PathVariable String keyword){
        BaseResponse response = null;
        try{
            List<Info> infoList = infoService.searchByTitle(keyword);
            if(infoList == null){
                response = new BaseResponse("success", null);
                return response;

            }
            List<InfoDTO> collect = infoList.stream()
                    .map(m-> new InfoDTO(m))
                    .collect(Collectors.toList());
            response = new BaseResponse("success", collect);
        }catch(Exception e){
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "내용으로 글 검색", notes = "검색 결과 있으면 data값으로 List 형식으로 반환 / 없으면 null반환", response = BaseResponse.class)
    @GetMapping("/searchContent/{keyword}")
    public BaseResponse searchByContent(@ApiParam(value = "내용 검색 키워드")@PathVariable String keyword){
        BaseResponse response = null;
        try{
            List<Info> infoList = infoService.searchByContent(keyword);
            if(infoList == null){
                response = new BaseResponse("success", null);
                return response;

            }
            List<InfoDTO> collect = infoList.stream()
                    .map(m-> new InfoDTO(m))
                    .collect(Collectors.toList());
            response = new BaseResponse("success", collect);
        }catch(Exception e){
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }


}
