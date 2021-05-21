package com.ssafy.bts.Service;

import com.ssafy.bts.Domain.GuestBook.GuestBook;
import com.ssafy.bts.Domain.Room.Room;
import com.ssafy.bts.Domain.User.User;
import com.ssafy.bts.Repository.GuestBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GuestBookService {
    private final GuestBookRepository guestBookRepository;

    /**
     * 방명록 테이블에 접속 기록 넣기
     * @return
     */
    @Transactional
    public GuestBook save(GuestBook guestBook) {
        return guestBookRepository.save(guestBook);
    }

    /**
     * 현재 방번호에 기록된 모든 리스트 조회
     * @return
     */
    @Transactional
    public List<GuestBook> findByRoom(Room room) {
        return guestBookRepository.findByRoom(room);
    }

    /**
     * 현재 방번호에 기록된 모든 리스트 조회
     * @return
     */
    @Transactional
    public GuestBook findByRoomAndUser(Room room, User user) {
        return guestBookRepository.findByRoomAndUser(room, user);
    }

    /**
     * 현재 유저 게스트북 방문기록 수정
     * @return
     */
    @Transactional
    public void updateGuestBook(Room room, User user, GuestBook guestBook) {
        Optional<GuestBook> gb = Optional.ofNullable(guestBookRepository.findByRoomAndUser(room, user));
        if(gb.isPresent()) { // 값이 있는지 확인 => .isPresent()
            gb.get().setVisitDate(guestBook.getVisitDate());
        }
        else{
            throw new IllegalStateException("잘못된 유저 아이디입니다.");
        }
    }
}
