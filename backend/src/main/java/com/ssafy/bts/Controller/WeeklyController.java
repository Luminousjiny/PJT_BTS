package com.ssafy.bts.Controller;

import com.ssafy.bts.Controller.Request.WeeklyRequest;
import com.ssafy.bts.Domain.User.User;
import com.ssafy.bts.Domain.Weekly.Weekly;
import com.ssafy.bts.Domain.Weekly.WeeklyDTO;
import com.ssafy.bts.Service.UserService;
import com.ssafy.bts.Service.WeeklyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Api
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/weekly")
@RequiredArgsConstructor
public class WeeklyController {
    private final WeeklyService weeklyService;
    private final UserService userService;

    @ApiOperation(value = "플랜 작성", notes = "플랜 작성 성공시 data값으로 '작성 성공' 설정 후 반환", response = BaseResponse.class)
    @PostMapping
    public BaseResponse writeWeekly(@ApiParam(value = "Weekly 객체", required=true) @RequestBody WeeklyRequest request) throws IOException {
        BaseResponse response = null;

        try{
            Weekly weekly = Weekly.createWeekly(request);
            User user = userService.findByUserId(request.getUserId());
            weekly.setUser(user);
            weeklyService.save(weekly);
            response = new BaseResponse("success", "작성 성공");
        }catch(IllegalStateException | ParseException e){
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "플랜 수정", notes = "플랜 수정 성공시 data값으로 '수정 성공' 설정 후 반환", response = BaseResponse.class)
    @PutMapping
    public BaseResponse updateWeekly(@ApiParam(value = "Weeklky 객체", required=true) @RequestBody WeeklyRequest request) {
        BaseResponse response = null;
        try {
            //mysql-java 9시간 차이 해결
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            Calendar cal = Calendar.getInstance();
            cal.setTime(request.getWeekStartTime());
            cal.add(Calendar.HOUR, -9);
            String startTime = sdf.format(cal.getTime());

            cal.setTime(request.getWeekEndTime());
            cal.add(Calendar.HOUR, -9);
            String endTime = sdf.format(cal.getTime());

            request.setWeekStartTime(new SimpleDateFormat("HH:mm:ss").parse(startTime));
            request.setWeekEndTime(new SimpleDateFormat("HH:mm:ss").parse(endTime));

            weeklyService.updateWeekly(request);
            response = new BaseResponse("success", "수정 성공");
        } catch (IllegalStateException | IOException | ParseException e) {
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "플랜 삭제", notes = "플랜 삭제 성공시 data값으로 '삭제 성공' 설정 후 반환", response = BaseResponse.class)
    @DeleteMapping("/{weekId}")
    public BaseResponse delteWeekly(@ApiParam(value = "플랜 번호")@PathVariable int weekId) {
        BaseResponse response = null;
        try {
            weeklyService.deleteWeekly(weekId);
            response = new BaseResponse("success", "삭제 성공");
        } catch (IllegalStateException e) {
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "현재 년도, 주에 작성된 플랜 리스트 조회", notes = "List 형식 반환(날짜/시작 시간순 오름차순)", response = BaseResponse.class)
    @GetMapping("/{userId}/{weekYear}/{weekMonth}/{startDate}/{endDate}")
    public BaseResponse findThisWeekly(@ApiParam(value = "사용자 아이디")@PathVariable String userId,
                                       @ApiParam(value = "현재 년도")@PathVariable int weekYear,
                                       @ApiParam(value = "현재 달")@PathVariable int weekMonth,
                                       @ApiParam(value = "현재 주 시작날짜")@PathVariable int startDate,
                                       @ApiParam(value = "현재 주 끝나는 날짜")@PathVariable int endDate){
        BaseResponse response = null;
        try{
            User user = userService.findByUserId(userId);
            List<Weekly> weeklyList  = weeklyService.findThisWeekly(user, weekYear, weekMonth, startDate, endDate);
            List<WeeklyDTO> collect = weeklyList.stream()
                    .map(m-> new WeeklyDTO(m))
                    .collect(Collectors.toList());
            response = new BaseResponse("success", collect);
        }
        catch(Exception e){
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "현재 플랜 상세 보여주기", notes = "WeeklyDTO 형식으로 반환", response = BaseResponse.class)
    @GetMapping("/{weekId}")
    public BaseResponse findWeeklyDetail(@ApiParam(value = "플랜 번호")@PathVariable int weekId){
        BaseResponse response = null;
        try{
            Weekly weekly  = weeklyService.findByWeekId(weekId);
            WeeklyDTO weeklyDTO = new WeeklyDTO(weekly);
            response = new BaseResponse("success", weeklyDTO);
        }
        catch(Exception e){
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }
}
