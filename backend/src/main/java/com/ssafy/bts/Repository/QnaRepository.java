package com.ssafy.bts.Repository;

import com.ssafy.bts.Domain.Qna.Qna;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QnaRepository extends JpaRepository<Qna, Long> {
    Qna findByQnaId(int qnaId);
    List<Qna> findByQnaTitleContaining(String keyword);
    List<Qna> findByQnaContentContaining(String keyword);
}
