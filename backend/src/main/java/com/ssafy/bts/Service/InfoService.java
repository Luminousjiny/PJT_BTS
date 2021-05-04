package com.ssafy.bts.Service;

import com.ssafy.bts.Controller.Request.InfoRequest;
import com.ssafy.bts.Domain.Info.Info;
import com.ssafy.bts.Repository.InfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InfoService {
    private final InfoRepository infoRepository;

    /**
     * 글 작성
     * @return
     */
    @Transactional
    public Info save(Info info){
        return infoRepository.save(info);
    }

    /**
     * 글 수정
     * @return
     */
    @Transactional
    public void updateInfo(InfoRequest request) throws IOException {
        Optional<Info> findInfo = Optional.ofNullable(infoRepository.findByInfoId(request.getInfoId()));
        if(findInfo.isPresent()) {
            findInfo.get().setInfoTitle(request.getInfoTitle());
            findInfo.get().setInfoContent(request.getInfoContent());
            findInfo.get().setInfoDate(new Date());
        }
        else{
            throw new IllegalStateException("존재하지 않는 글입니다.");
        }
    }

    /**
     * 글 삭제
     * @return
     */
    @Transactional
    public void deleteInfo(int infoId) {
        Optional<Info> deleteInfo = Optional.ofNullable(infoRepository.findByInfoId(infoId));
        if(deleteInfo.isPresent()){
            infoRepository.delete(deleteInfo.get());
        }else{
            throw new IllegalStateException("존재하지 않는 글입니다.");
        }
    }

    /**
     * 전체 글 목록 조회
     * @return
     */
    @Transactional
    public List<Info> findAll() {
        return infoRepository.findAll();
    }

    /**
     * 현재 글 아이디 상세정보 조회
     * @return
     */
    @Transactional
    public Info findByInfoId(int infoId) { return infoRepository.findByInfoId(infoId);}

    /**
     * 제목 검색 결과 글리스트 조회
     * @return
     */
    @Transactional
    public List<Info> searchByTitle(String keyword) {
        return infoRepository.findByInfoTitleContaining(keyword);
    }

    /**
     * 내용 검색 결과 글리스트 조회
     * @return
     */
    @Transactional
    public List<Info> searchByContent(String keyword) {
        return infoRepository.findByInfoContentContaining(keyword);
    }
}
