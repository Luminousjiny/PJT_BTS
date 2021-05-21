package com.ssafy.bts.Repository;

import com.ssafy.bts.Domain.Qna.Qna;
import com.ssafy.bts.Domain.Room.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QnaRepository extends JpaRepository<Qna, Long> {
    Qna findByQnaId(int qnaId);

    @Query("SELECT q FROM Qna q WHERE q.room = :room and q.qnaTitle LIKE %:keyword%")
    List<Qna> findByQnaTitleContaining(String keyword, Room room);

    @Query("SELECT q FROM Qna q WHERE q.room = :room and q.qnaTitle LIKE %:keyword%")
    List<Qna> findByQnaContentContaining(String keyword, Room room);
    List<Qna> findByRoom(Room room);
}
