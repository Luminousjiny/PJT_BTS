package com.ssafy.bts.Controller;

import com.ssafy.bts.Controller.Request.QnaRequest;
import com.ssafy.bts.Domain.Info.Info;
import com.ssafy.bts.Domain.Info.InfoDTO;
import com.ssafy.bts.Domain.Qna.Qna;
import com.ssafy.bts.Domain.Qna.QnaDTO;
import com.ssafy.bts.Domain.User.User;
import com.ssafy.bts.Service.QnaService;
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
@RequestMapping("/v1/qna")
@RequiredArgsConstructor
public class QnaController {
    private final QnaService qnaService;
    private final UserService userService;

    @ApiOperation(value = "Qna 질문 작성", notes = "Qna 질문작성 성공 시 data값으로 '작성 성공' 설정 후 반환", response = BaseResponse.class)
    @PostMapping
    public BaseResponse writeQna(@ApiParam(value = "Qna 객체", required=true) @RequestBody QnaRequest request) throws IOException {
        BaseResponse response = null;

        try{
            Qna qna = Qna.createQna(request);
            User user = userService.findByUserId(request.getUserId());
            qna.setUser(user);
            qnaService.save(qna);
            response = new BaseResponse("success", "작성 성공");
        }catch(IllegalStateException e){
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "Qna 질문수정", notes = "Qna 질문 수정 성공시 data값으로 '수정 성공' 설정 후 반환", response = BaseResponse.class)
    @PutMapping
    public BaseResponse updateQna(@ApiParam(value = "리뷰 객체", required=false) @RequestBody QnaRequest request) {
        BaseResponse response = null;
        try {
            qnaService.updateQna(request);
            response = new BaseResponse("success", "수정 성공");
        } catch (IllegalStateException | IOException e) {
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "Qna 질문삭제", notes = "Qna 질문 삭제 성공시 data값으로 '삭제 성공' 설정 후 반환", response = BaseResponse.class)
    @DeleteMapping("/{qnaId}")
    public BaseResponse deleteQna(@ApiParam(value = "글 아이디")@PathVariable int qnaId) {
        BaseResponse response = null;
        try {
            qnaService.deleteQna(qnaId);
            response = new BaseResponse("success", "삭제 성공");
        } catch (IllegalStateException e) {
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "Qna 질문 전체 리스트 조회", notes = "List 형식으로 반환", response = BaseResponse.class)
    @GetMapping("/list")
    public BaseResponse findAllQna(){
        BaseResponse response = null;
        try{
            List<Qna> qnaList  = qnaService.findAll();
            List<QnaDTO> collect = qnaList.stream()
                    .map(m-> new QnaDTO(m))
                    .collect(Collectors.toList());
            response = new BaseResponse("success", collect);
        }
        catch(Exception e){
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "현재 Qna 질문 상세 보여주기", notes = "QnaDto 형식으로 반환", response = BaseResponse.class)
    @GetMapping("/detail/{qnaId}")
    public BaseResponse detailQna(@ApiParam(value = "qna 아이디")@PathVariable int qnaId){
        BaseResponse response = null;
        try{
            Qna qna = qnaService.findByQnaId(qnaId);
            QnaDTO qnaDTO = new QnaDTO(qna);
            response = new BaseResponse("success", qnaDTO);
        }catch(IllegalStateException e){
            response = new BaseResponse("fail",e.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "제목으로 질문 검색", notes = "검색 결과 있으면 data값으로 List 형식으로 반환 / 없으면 null반환", response = BaseResponse.class)
    @GetMapping("/searchTitle/{keyword}")
    public BaseResponse searchByTitle(@ApiParam(value = "제목 검색 키워드")@PathVariable String keyword){
        BaseResponse response = null;
        try{
            List<Qna> qnaList = qnaService.searchByTitle(keyword);
            if(qnaList == null){
                response = new BaseResponse("success", null);
                return response;

            }
            List<QnaDTO> collect = qnaList.stream()
                    .map(m-> new QnaDTO(m))
                    .collect(Collectors.toList());
            response = new BaseResponse("success", collect);
        }catch(Exception e){
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "내용으로 질문 검색", notes = "검색 결과 있으면 data값으로 List 형식으로 반환 / 없으면 null반환", response = BaseResponse.class)
    @GetMapping("/searchContent/{keyword}")
    public BaseResponse searchByContent(@ApiParam(value = "내용 검색 키워드")@PathVariable String keyword){
        BaseResponse response = null;
        try{
            List<Qna> qnaList = qnaService.searchByContent(keyword);
            if(qnaList == null){
                response = new BaseResponse("success", null);
                return response;

            }
            List<QnaDTO> collect = qnaList.stream()
                    .map(m-> new QnaDTO(m))
                    .collect(Collectors.toList());
            response = new BaseResponse("success", collect);
        }catch(Exception e){
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }
}
