package com.ssafy.bts.Service;

import com.ssafy.bts.Domain.Info.Info;
import com.ssafy.bts.Repository.InfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class InfoService {
    private final InfoRepository infoRepository;

    /**
     * 정보 글 작성
     * @return
     */
    @Transactional
    public Info save(Info info){
        return infoRepository.save(info);
    }
}
