package com.ssafy.bts.Service;

import com.ssafy.bts.Controller.Request.CodeRequest;
import com.ssafy.bts.Domain.Coding.Code;
import com.ssafy.bts.Domain.Coding.Problem;
import com.ssafy.bts.Repository.CodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CodeService {
    private final CodeRepository codeRepository;

    /**
     * 코드 작성
     * @return
     */
    @Transactional
    public Code save(Code c) { return codeRepository.save(c); }

    /**
     * 코드 수정
     * @return
     */
    @Transactional
    public void updateCode(CodeRequest request) {
        Optional<Code> findCode = Optional.ofNullable(codeRepository.findByCodeId(request.getCodeId()));
        if(findCode.isPresent()) {
            findCode.get().setCodeMemory(request.getCodeMemory());
            findCode.get().setCodeTime(request.getCodeTime());
            findCode.get().setCodeLan(request.getCodeLan());
            findCode.get().setCodeContent(request.getCodeContent());
            findCode.get().setCodeDate(new Date());
        }
        else{
            throw new IllegalStateException("존재하지 않는 코드입니다.");
        }
    }

    /**
     * 코드 삭제
     * @return
     */
    @Transactional
    public void deleteCode(int codeId) {
        Optional<Code> deleteCode = Optional.ofNullable(codeRepository.findByCodeId(codeId));
        if(deleteCode.isPresent()){
            codeRepository.delete(deleteCode.get());
        }else{
            throw new IllegalStateException("존재하지 않는 코드입니다.");
        }
    }

    /**
     * 현재 문제에 제출된 코드 리스트 조회
     * @return
     */
    @Transactional
    public List<Code> findByProblem(Problem problem) {
        return codeRepository.findByProblem(problem);
    }

    /**
     * 코드 상세 가져오기
     * @return
     */
    @Transactional
    public Code findByCodeId(int codeId) {
        return codeRepository.findByCodeId(codeId);
    }
}
