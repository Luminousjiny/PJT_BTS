package com.ssafy.bts.Controller;

import com.ssafy.bts.Controller.Request.MonthlyRequest;
import com.ssafy.bts.Domain.Monthly.Monthly;
import com.ssafy.bts.Domain.Monthly.MonthlyDTO;
import com.ssafy.bts.Domain.User.User;
import com.ssafy.bts.Service.MonthlyService;
import com.ssafy.bts.Service.UserService;
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
@RequestMapping("/v1/monthly")
@RequiredArgsConstructor
public class MonthlyController {
    private final MonthlyService monthlyService;
    private final UserService userService;

    @ApiOperation(value = "플랜 작성", notes = "플랜 작성 성공시 data값으로 '작성 성공' 설정 후 반환", response = BaseResponse.class)
    @PostMapping
    public BaseResponse writeMonthly(@ApiParam(value = "Monthly 객체", required=true) @RequestBody MonthlyRequest request) throws IOException {
        BaseResponse response = null;

        try{
            Monthly monthly = Monthly.createMonthly(request);
            User user = userService.findByUserId(request.getUserId());
            monthly.setUser(user);
            monthlyService.save(monthly);
            response = new BaseResponse("success", "작성 성공");
        }catch(IllegalStateException | ParseException e){
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "플랜 수정", notes = "플랜 수정 성공시 data값으로 '수정 성공' 설정 후 반환", response = BaseResponse.class)
    @PutMapping
    public BaseResponse updateMonthly(@ApiParam(value = "Monthly 객체", required=true) @RequestBody MonthlyRequest request) {
        BaseResponse response = null;
        try {
            //mysql-java 9시간 차이 해결
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance();
            cal.setTime(request.getMonStartDate());
            cal.add(Calendar.HOUR, -9);
            String startTime = sdf.format(cal.getTime());

            cal.setTime(request.getMonEndDate());
            cal.add(Calendar.HOUR, -9);
            String endTime = sdf.format(cal.getTime());

            request.setMonStartDate(new SimpleDateFormat("yyyy-MM-dd").parse(startTime));
            request.setMonEndDate(new SimpleDateFormat("yyyy-MM-dd").parse(endTime));

            monthlyService.updateMonthly(request);
            response = new BaseResponse("success", "수정 성공");
        } catch (IllegalStateException | IOException | ParseException e) {
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "플랜 삭제", notes = "플랜 삭제 성공시 data값으로 '삭제 성공' 설정 후 반환", response = BaseResponse.class)
    @DeleteMapping("/{monId}")
    public BaseResponse deleteMonthly(@ApiParam(value = "플랜 번호")@PathVariable int monId) {
        BaseResponse response = null;
        try {
            monthlyService.deleteMonthly(monId);
            response = new BaseResponse("success", "삭제 성공");
        } catch (IllegalStateException e) {
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "현재 년도, 달에 작성된 플랜 리스트 조회", notes = "List 형식으로 반환", response = BaseResponse.class)
    @GetMapping("/{monYear}/{monMonth}")
    public BaseResponse findThisMonthly(@ApiParam(value = "현재 년도")@PathVariable int monYear,
                                        @ApiParam(value = "현재 달")@PathVariable int monMonth){
        BaseResponse response = null;
        try{
            List<Monthly> montlyList  = monthlyService.findByMonYearAndMonMonth(monYear, monMonth);
            List<MonthlyDTO> collect = montlyList.stream()
                    .map(m-> new MonthlyDTO(m))
                    .collect(Collectors.toList());
            response = new BaseResponse("success", collect);
        }
        catch(Exception e){
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "현재 플랜 상세 보여주기", notes = "MonthlyDTO 형식으로 반환", response = BaseResponse.class)
    @GetMapping("/{monId}")
    public BaseResponse findMonthlyDetail(@ApiParam(value = "플랜 번호")@PathVariable int monId){
        BaseResponse response = null;
        try{
            Monthly monthly  = monthlyService.findByMonId(monId);
            MonthlyDTO monthlyDTO = new MonthlyDTO(monthly);
            response = new BaseResponse("success", monthlyDTO);
        }
        catch(Exception e){
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }
}
