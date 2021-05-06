package com.ssafy.bts.Service;

import com.ssafy.bts.Controller.Request.CommentRequest;
import com.ssafy.bts.Domain.Comment.Comment;
import com.ssafy.bts.Domain.Qna.Qna;
import com.ssafy.bts.Repository.CommentRepository;
import com.ssafy.bts.Repository.QnaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final QnaRepository qnaRepository;

    /**
     * 댓글 작성하기
     */
    @Transactional
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    /**
     * 댓글 수정하기
     */
    @Transactional
    public void updateComment(CommentRequest request) {
        Qna qna = qnaRepository.findByQnaId(request.getQnaId());
        Optional<Comment> findComment = Optional.ofNullable(commentRepository.findByComIdAndQna(request.getComId(), qna));
        if(findComment.isPresent()) {
            System.out.println(findComment.get());
            findComment.get().setComContent(request.getComContent());
            findComment.get().setComDate(new Date());
        }
        else{
            throw new IllegalStateException("존재하지 않는 리뷰입니다.");
        }
    }

    /**
     * 댓글 삭제하기
     */
    @Transactional
    public void deleteComment(int comId) {
        Optional<Comment> deleteComment = Optional.ofNullable(commentRepository.findByComId(comId));
        if(deleteComment.isPresent()){
            commentRepository.delete(deleteComment.get());
        }
    }

    /**
     * 현재 질문의 전체 댓글 목록 가져오기 (최신순)
     */
    @Transactional
    public List<Comment> findByQna(Qna qna) {
        List<Comment> commentList = commentRepository.findByQna(qna);
        return commentList;
    }
}
