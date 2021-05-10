package com.ssafy.bts.Controller;

import com.ssafy.bts.Controller.Request.CommentRequest;
import com.ssafy.bts.Domain.Comment.Comment;
import com.ssafy.bts.Domain.Comment.CommentDTO;
import com.ssafy.bts.Domain.Qna.Qna;
import com.ssafy.bts.Domain.User.User;
import com.ssafy.bts.Service.CommentService;
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
@RequestMapping("/v1/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final UserService userService;
    private final QnaService qnaService;

    @ApiOperation(value = "댓글 작성", notes = "댓글 작성 성공시 BaseResponse에 data값으로 전체 리스트 반환", response = BaseResponse.class)
    @PostMapping
    public BaseResponse writeComment(@ApiParam(value = "댓글 객체", required=true) @RequestBody CommentRequest request) throws IOException {
        BaseResponse response = null;

        try{
            Comment comment = Comment.createComment(request);
            User user = userService.findByUserId(request.getUserId());
            Qna qna = qnaService.findByQnaId(request.getQnaId());
            comment.setUser(user);
            comment.setQna(qna);
            if(qna != null && user != null){
                commentService.save(comment);

                //전체 리스트 반환
                Qna q = qnaService.findByQnaId(request.getQnaId());
                List<Comment> commentList  = commentService.findByQna(q);
                List<CommentDTO> collect = commentList.stream()
                        .map(m-> new CommentDTO(m))
                        .collect(Collectors.toList());
                response = new BaseResponse("success", collect);
            }else{
                response = new BaseResponse("success", "작성 실패");
            }

        }catch(IllegalStateException e){
            response = new BaseResponse("fail",e.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "댓글 수정", notes = "댓글 수정 성공시 data값으로 상세정보 반환", response = BaseResponse.class)
    @PutMapping
    public BaseResponse updateComment(@ApiParam(value = "댓글 객체", required=false) @RequestBody CommentRequest request) {
        BaseResponse response = null;
        try {
            commentService.updateComment(request);

            //댓글 상세정보 반환
            Comment comment = commentService.findByComId(request.getComId());
            CommentDTO commentDTO = new CommentDTO(comment);
            response = new BaseResponse("success", commentDTO);
        } catch (IllegalStateException e) {
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "댓글 삭제", notes = "반환되는 데이터는 삭제 성공 / 에러 메시지", response = BaseResponse.class)
    @DeleteMapping("/{comId}")
    public BaseResponse deleteComment(@ApiParam(value = "댓글 아이디")@PathVariable int comId){
        BaseResponse response = null;
        try {
            commentService.deleteComment(comId);
            response = new BaseResponse("success", "삭제 성공");
        } catch (IllegalStateException e) {
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "현재 질문에 작성된 전체 댓글목록 조회(Qna 상세페이지)", notes = "List 형식으로 반환", response = BaseResponse.class)
    @GetMapping("/list/{qnaId}")
    public BaseResponse findAllComment(@ApiParam(value = "질문번호")@PathVariable int qnaId){
        BaseResponse response = null;
        try{
            Qna qna = qnaService.findByQnaId(qnaId);
            List<Comment> commentList  = commentService.findByQna(qna);
            List<CommentDTO> collect = commentList.stream()
                    .map(m-> new CommentDTO(m))
                    .collect(Collectors.toList());

            response = new BaseResponse("success", collect);
        }
        catch(Exception e){
            response = new BaseResponse("fail", e.getMessage());
        }
        return response;
    }


}
