package com.ssafy.bts.Service;

import com.ssafy.bts.Controller.Request.MonthlyRequest;
import com.ssafy.bts.Domain.Monthly.Monthly;
import com.ssafy.bts.Domain.User.User;
import com.ssafy.bts.Repository.MonthlyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MonthlyService {

    private final MonthlyRepository monthlyRepository;

    /**
     * 플랜 작성
     *
     * @return
     */
    @Transactional
    public Monthly save(Monthly monthly) {
        return monthlyRepository.save(monthly);
    }


    /**
     * 플랜 수정
     *
     * @return
     */
    @Transactional
    public void updateMonthly(MonthlyRequest request) throws IOException {
        Optional<Monthly> findMonthly = Optional.ofNullable(monthlyRepository.findByMonId(request.getMonId()));
        if (findMonthly.isPresent()) {
            findMonthly.get().setMonColor(request.getMonColor());
            findMonthly.get().setMonContent(request.getMonContent());
            findMonthly.get().setMonEndDate(request.getMonEndDate());
            findMonthly.get().setMonStartDate(request.getMonStartDate());
            findMonthly.get().setMonContent(request.getMonContent());
            findMonthly.get().setMonColor(request.getMonColor());
            findMonthly.get().setMonMonth(request.getMonMonth());
            findMonthly.get().setMonYear(request.getMonYear());
        } else {
            throw new IllegalStateException("존재하지 않는 플랜입니다.");
        }
    }

    /**
     * 플랜 삭제
     * @return
     */
    @Transactional
    public void deleteMonthly(int monId) {
        Optional<Monthly> deleteMonthly = Optional.ofNullable(monthlyRepository.findByMonId(monId));
        if(deleteMonthly.isPresent()){
            monthlyRepository.delete(deleteMonthly.get());
        }else{
            throw new IllegalStateException("존재하지 않는 플랜입니다.");
        }
    }

    /**
     * 현재 년도의 달에 작성된 플랜 조회
     * @return
     */
    @Transactional
    public List<Monthly> findByUserIdAndMonYearAndMonMonth(User user, int monYear, int monMonth) { return monthlyRepository.findByUserIdAndMonYearAndMonMonth(user, monYear, monMonth);}

    /**
     * 플랜 아이디 상세정보 가져오기
     * @return
     */
    @Transactional
    public Monthly findByMonId(int monId) {
        return monthlyRepository.findByMonId(monId);
    }
}
