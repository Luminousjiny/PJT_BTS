package com.ssafy.bts.Repository;

import com.ssafy.bts.Domain.Comment.Comment;
import com.ssafy.bts.Domain.Qna.Qna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Comment findByComId(int comId);

    //댓글 리스트 최신순 정렬
    @Query("select c from Comment c where c.qna = :qna order by c.comId desc")
    List<Comment> findByQna(Qna qna);

    Comment findByComIdAndQna(int comId, Qna qna);
}
