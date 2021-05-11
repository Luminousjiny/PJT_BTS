package com.ssafy.bts.Service;

import com.ssafy.bts.Controller.Request.ProblemRequest;
import com.ssafy.bts.Domain.Coding.Problem;
import com.ssafy.bts.Domain.Room.Room;
import com.ssafy.bts.Repository.ProblemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProblemService {
    private final ProblemRepository problemRepository;

    /**
     * 문제 작성
     * @return
     */
    @Transactional
    public Problem save(Problem p) { return problemRepository.save(p);}

    /**
     * 문제 수정
     * @return
     */
    @Transactional
    public void updateProblem(ProblemRequest request) {
        Optional<Problem> findInfo = Optional.ofNullable(problemRepository.findByProId(request.getProId()));
        if(findInfo.isPresent()) {
            findInfo.get().setProTitle(request.getProTitle());
            findInfo.get().setProContent(request.getProContent());
            findInfo.get().setProDate(new Date());
        }
        else{
            throw new IllegalStateException("존재하지 않는 문제입니다.");
        }
    }

    /**
     * 문제 삭제
     * @return
     */
    @Transactional
    public void deleteProblem(int proId) {
        Optional<Problem> deleteProblem = Optional.ofNullable(problemRepository.findByProId(proId));
        if(deleteProblem.isPresent()){
            problemRepository.delete(deleteProblem.get());
        }else{
            throw new IllegalStateException("존재하지 않는 문제입니다.");
        }
    }

    /**
     * 전체 문제 목록 조회
     * @return
     */
    @Transactional
    public List<Problem> findAll() { return problemRepository.findAll(); }


    /**
     * 현재 문제 상세 가져오기
     * @return
     */
    @Transactional
    public Problem findByProId(int proId) { return problemRepository.findByProId(proId);}


    /**
     * 제목으로 문제 검색
     * @return
     */
    @Transactional
    public List<Problem> searchByTitle(Room room, String keyword) {
        return problemRepository.findByProTitleContaining(room, keyword);
    }

    /**
     * 내용으로 문제 검색
     * @return
     */
    @Transactional
    public List<Problem> searchByContent(Room room, String keyword) {
        return problemRepository.findByProContentContaining(room, keyword);
    }

    /**
     * 현재 방번호에 있는 모든 문제 가져오기
     * @return
     */
    @Transactional
    public List<Problem> findByRoom(Room room) {
        return problemRepository.findByRoom(room);
    }
}
