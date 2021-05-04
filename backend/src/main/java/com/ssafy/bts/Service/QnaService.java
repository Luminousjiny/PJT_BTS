package com.ssafy.bts.Service;

import com.ssafy.bts.Controller.Request.QnaRequest;
import com.ssafy.bts.Domain.Qna.Qna;
import com.ssafy.bts.Repository.QnaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QnaService {
    private final QnaRepository qnaRepository;
    /**
     * 질문 작성
     */
    @Transactional
    public Qna save(Qna qna) {
        return qnaRepository.save(qna);
    }

    /**
     * 질문 수정
     */
    @Transactional
    public void updateQna(QnaRequest request) throws IOException {
        Optional<Qna> findQna = Optional.ofNullable(qnaRepository.findByQnaId(request.getQnaId()));
        if(findQna.isPresent()){
            findQna.get().setQnaTitle(request.getQnaTitle());
            findQna.get().setQnaContent(request.getQnaContent());
            findQna.get().setQnaDate(new Date());
        }else{
            throw new IllegalStateException("존재하지 않는 질문 입니다.");
        }
    }

    /**
     * 질문 삭제
     */
    @Transactional
    public void deleteQna(int qnaId) {
        Optional<Qna> deleteQna = Optional.ofNullable(qnaRepository.findByQnaId(qnaId));
        if(deleteQna.isPresent()){
            qnaRepository.delete(deleteQna.get());
        }else{
            throw new IllegalStateException("존재하지 않는 질문 입니다.");
        }
    }

    /**
     * 질문 전체목록 조회
     */
    @Transactional
    public List<Qna> findAll() {
        return qnaRepository.findAll();
    }

    /**
     * 현재 QnaI로 상세정보 조회
     * @return
     */
    @Transactional
    public Qna findByQnaId(int qnaId) {
        return qnaRepository.findByQnaId(qnaId);
    }

    /**
     * 제목 검색 결과 글리스트 조회
     */
    @Transactional
    public List<Qna> searchByTitle(String keyword) {
        return qnaRepository.findByQnaTitleContaining(keyword);
    }

    /**
     * 내용 검색 결과 질문 리스트 조회
     */
    @Transactional
    public List<Qna> searchByContent(String keyword) {
        return qnaRepository.findByQnaContentContaining(keyword);
    }
}
