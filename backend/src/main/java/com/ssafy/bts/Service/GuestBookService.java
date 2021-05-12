package com.ssafy.bts.Service;

import com.ssafy.bts.Domain.GuestBook.GuestBook;
import com.ssafy.bts.Domain.Room.Room;
import com.ssafy.bts.Repository.GuestBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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
}
