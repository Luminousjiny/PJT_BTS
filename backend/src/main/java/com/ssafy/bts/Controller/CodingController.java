package com.ssafy.bts.Controller;

import com.ssafy.bts.Controller.Request.CodeRequest;
import com.ssafy.bts.Controller.Request.ProblemRequest;
import com.ssafy.bts.Controller.Request.SolveRequest;
import com.ssafy.bts.Domain.Coding.*;
import com.ssafy.bts.Domain.User.User;
import com.ssafy.bts.Service.CodeService;
import com.ssafy.bts.Service.ProblemService;
import com.ssafy.bts.Service.SolveService;
import com.ssafy.bts.Service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Api
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/")
@RequiredArgsConstructor
public class CodingController {
    private final ProblemService problemService;
    private final CodeService codeService;
    private final UserService userService;
    private final SolveService solveService;

    @ApiOperation(value = "문제 작성", notes = "문제 작성 성공시 data값으로 '작성 성공' 설정 후 반환", response = BaseResponse.class)
    @PostMapping("/pro")
    public BaseResponse writeProblem(@ApiParam(value = "Problem 객체", required=true) @RequestBody ProblemRequest request) throws IOException {
        BaseResponse response = null;

        try{
            Problem p = Problem.createProblem(request);
            User user = userService.findByUserId(request.getUserId());
            p.setUser(user);
            problemService.save(p);
            response = new BaseResponse("success", "작성 성공");
        }catch(IllegalStateException e){
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "문제 수정", notes = "문제 수정 성공시 data값으로 '수정 성공' 설정 후 반환", response = BaseResponse.class)
    @PutMapping("/pro")
    public BaseResponse updateProblem(@ApiParam(value = "Problem 객체", required=false) @RequestBody ProblemRequest request) {
        BaseResponse response = null;
        try {
            problemService.updateProblem(request);
            response = new BaseResponse("success", "수정 성공");
        } catch (IllegalStateException e) {
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "문제 삭제", notes = "문제 삭제 성공시 data값으로 '삭제 성공' 설정 후 반환", response = BaseResponse.class)
    @DeleteMapping("/pro/{proId}")
    public BaseResponse deleteProblem(@ApiParam(value = "문제 번호")@PathVariable int proId) {
        BaseResponse response = null;
        try {
            problemService.deleteProblem(proId);
            response = new BaseResponse("success", "삭제 성공");
        } catch (IllegalStateException e) {
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "전체 문제 리스트 조회", notes = "List 형식으로 반환", response = BaseResponse.class)
    @GetMapping("/pro/list")
    public BaseResponse findAllProblem(){
        BaseResponse response = null;
        try{
            List<Problem> problemList  = problemService.findAll();
            List<ProblemDTO> collect = problemList.stream()
                    .map(m-> new ProblemDTO(m))
                    .collect(Collectors.toList());
            response = new BaseResponse("success", collect);
        }
        catch(Exception e){
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "현재 문제 상세 보여주기", notes = "ProblemDto 형식으로 반환", response = BaseResponse.class)
    @GetMapping("/pro/detail/{proId}")
    public BaseResponse detailProblem(@ApiParam(value = "문제 번호")@PathVariable int proId){
        BaseResponse response = null;
        try{
            Problem problem = problemService.findByProId(proId);
            ProblemDTO problemDTO = new ProblemDTO(problem);
            response = new BaseResponse("success", problemDTO);
        }catch(IllegalStateException e){
            response = new BaseResponse("fail",e.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "코드 작성", notes = "코드 작성 성공시 data값으로 '작성 성공' 설정 후 반환", response = BaseResponse.class)
    @PostMapping("/code")
    public BaseResponse writeCode(@ApiParam(value = "Code 객체", required=true) @RequestBody CodeRequest request) throws IOException {
        BaseResponse response = null;

        try{
            Code c = Code.createCode(request);
            User user = userService.findByUserId(request.getUserId());
            Problem problem = problemService.findByProId(request.getProId());
            c.setUser(user);
            c.setProblem(problem);
            codeService.save(c);
            response = new BaseResponse("success", "작성 성공");
        }catch(IllegalStateException e){
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "코드 수정", notes = "코드 수정 성공시 data값으로 '수정 성공' 설정 후 반환", response = BaseResponse.class)
    @PutMapping("/code")
    public BaseResponse updateCode(@ApiParam(value = "Code 객체", required=false) @RequestBody CodeRequest request) {
        BaseResponse response = null;
        try {
            codeService.updateCode(request);
            response = new BaseResponse("success", "수정 성공");
        } catch (IllegalStateException e) {
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "코드 삭제", notes = "코드 삭제 성공시 data값으로 '삭제 성공' 설정 후 반환", response = BaseResponse.class)
    @DeleteMapping("/code/{codeId}")
    public BaseResponse deleteCode(@ApiParam(value = "코드 번호")@PathVariable int codeId) {
        BaseResponse response = null;
        try {
            codeService.deleteCode(codeId);
            response = new BaseResponse("success", "삭제 성공");
        } catch (IllegalStateException e) {
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "현재 문제에 제출된 코드 리스트 조회", notes = "List 형식으로 반환", response = BaseResponse.class)
    @GetMapping("/code/list/{proId}")
    public BaseResponse findAllCode(@ApiParam(value = "문제 번호")@PathVariable int proId){
        BaseResponse response = null;
        try{
            Problem problem = problemService.findByProId(proId);
            List<Code> codeList  = codeService.findByProblem(problem);
            List<CodeDTO> collect = codeList.stream()
                    .map(m-> new CodeDTO(m))
                    .collect(Collectors.toList());
            response = new BaseResponse("success", collect);
        }
        catch(Exception e){
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "현재 코드 상세 보여주기", notes = "CodeDTO 형식으로 반환", response = BaseResponse.class)
    @GetMapping("/code/detail/{codeId}")
    public BaseResponse detailCode(@ApiParam(value = "코드 번호")@PathVariable int codeId){
        BaseResponse response = null;
        try{
            Code code = codeService.findByCodeId(codeId);
            CodeDTO codeDTO = new CodeDTO(code);
            response = new BaseResponse("success", codeDTO);
        }catch(IllegalStateException e){
            response = new BaseResponse("fail",e.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "제출한 코드 맞으면 50포인트 적립, 레벨 업데이트", notes = "문제 처음 풀었으면 포인트 적립하고 1반환, 이미 푼 문제면 적립 안하고 0 반환 / 에러 메시지", response = BaseResponse.class)
    @PostMapping("/solve")
    public BaseResponse modifyHelpCnt(@ApiParam(value = "Solve 객체")@RequestBody SolveRequest request){
        BaseResponse response = null;
        int result = 0;
        try {
            Problem problem = problemService.findByProId(request.getProId());
            User user = userService.findByUserId(request.getUserId());
            Solve solve = solveService.findByProblemAnsUser(problem, user);
            if(solve == null){
                //문제푼 테이블에 넣기
                Solve sol = new Solve();
                user.setUserPoint(user.getUserPoint()+50);
                user.setUserLank(upDateLank(user.getUserPoint(), user.getUserLank()));
                userService.updateUser(user);//유저 포인트,레벨 업데이트
                sol.setUser(user);
                sol.setProblem(problem);
                solveService.save(sol); //문제 푼거 넣기
                result = 1;
            }
            response = new BaseResponse("success", result);
        } catch (IllegalStateException e) {
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "제목으로 문제 검색", notes = "검색 결과 있으면 data값으로 List 형식으로 반환 / 없으면 null반환", response = BaseResponse.class)
    @GetMapping("/pro/searchTitle/{keyword}")
    public BaseResponse searchByTitle(@ApiParam(value = "제목 검색 키워드")@PathVariable String keyword){
        BaseResponse response = null;
        try{
            List<Problem> problemList = problemService.searchByTitle(keyword);
            if(problemList == null){
                response = new BaseResponse("success", null);
                return response;

            }
            List<ProblemDTO> collect = problemList.stream()
                    .map(m-> new ProblemDTO(m))
                    .collect(Collectors.toList());
            response = new BaseResponse("success", collect);
        }catch(Exception e){
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "내용으로 문제 검색", notes = "검색 결과 있으면 data값으로 List 형식으로 반환 / 없으면 null반환", response = BaseResponse.class)
    @GetMapping("/pro/searchContent/{keyword}")
    public BaseResponse searchByContent(@ApiParam(value = "내용 검색 키워드")@PathVariable String keyword){
        BaseResponse response = null;
        try{
            List<Problem> problemList = problemService.searchByContent(keyword);
            if(problemList == null){
                response = new BaseResponse("success", null);
                return response;

            }
            List<ProblemDTO> collect = problemList.stream()
                    .map(m-> new ProblemDTO(m))
                    .collect(Collectors.toList());
            response = new BaseResponse("success", collect);
        }catch(Exception e){
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    //레벨 업데이트
    private String upDateLank(int userPoint, String userLank) {
        String updateLevel = "";
        switch(userPoint){
            case 500:
                updateLevel = "bronze2";
                break;
            case 1000:
                updateLevel = "bronze1";
                break;
            case 1500:
                updateLevel = "silver3";
                break;
            case 2000:
                updateLevel = "silver2";
                break;
            case 2500:
                updateLevel = "silver1";
                break;
            case 3000:
                updateLevel = "gold3";
                break;
            case 3500:
                updateLevel = "gold2";
                break;
            case 4000:
                updateLevel = "gold1";
                break;
            default:
                updateLevel = userLank;
                break;
        }
        return updateLevel;
    }
}
