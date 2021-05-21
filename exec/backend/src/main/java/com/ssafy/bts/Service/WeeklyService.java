package com.ssafy.bts.Service;

import com.ssafy.bts.Controller.Request.WeeklyRequest;
import com.ssafy.bts.Domain.User.User;
import com.ssafy.bts.Domain.Weekly.Weekly;
import com.ssafy.bts.Repository.WeeklyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WeeklyService {

    private final WeeklyRepository weeklyRepository;


    /**
     * 플랜 작성
     *
     * @return
     */
    @Transactional
    public Weekly save(Weekly weekly) {return weeklyRepository.save(weekly);}

    /**
     * 플랜 수정
     *
     * @return
     */
    @Transactional
    public void updateWeekly(WeeklyRequest request) throws IOException {
        Optional<Weekly> findWeekly = Optional.ofNullable(weeklyRepository.findByWeekId(request.getWeekId()));
        if (findWeekly.isPresent()) {
            findWeekly.get().setWeekDate(request.getWeekDate());
            findWeekly.get().setWeekContent(request.getWeekContent());
            findWeekly.get().setWeekEndTime(request.getWeekEndTime());
            findWeekly.get().setWeekStartTime(request.getWeekStartTime());
        }
        else throw new IllegalStateException("존재하지 않는 플랜입니다.");
    }

    /**
     * 플랜 삭제
     *
     * @return
     */
    @Transactional
    public void deleteWeekly(int weekId) {
        Optional<Weekly> deletWeekly = Optional.ofNullable(weeklyRepository.findByWeekId(weekId));
        if(deletWeekly.isPresent()){
            weeklyRepository.delete(deletWeekly.get());
        }else{
            throw new IllegalStateException("존재하지 않는 플랜입니다.");
        }
    }

    /**
     * 현재 주에 작성된 플랜 조회
     * @return
     */
    @Transactional
    public List<Weekly> findThisWeekly(User user, Date startDate, Date endDate) { return weeklyRepository.findThisWeekly(user, startDate, endDate);}

    /**
     * 현재 플랜 아이디 상세정보 가져오기
     * @return
     */
    @Transactional
    public Weekly findByWeekId(int weekId) {
        return weeklyRepository.findByWeekId(weekId);
    }

}
