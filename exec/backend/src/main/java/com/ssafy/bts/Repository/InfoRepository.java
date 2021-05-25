package com.ssafy.bts.Repository;

import com.ssafy.bts.Domain.Info.Info;
import com.ssafy.bts.Domain.Room.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface InfoRepository extends JpaRepository<Info, Long> {
    Info findByInfoId(int infoId);

    @Query("SELECT i FROM Info i WHERE i.room = :room and i.infoTitle LIKE %:keyword%")
    List<Info> findByInfoTitleContaining(Room room, String keyword); //Like검색 %{keyword}%

    @Query("SELECT i FROM Info i WHERE i.room = :room and i.infoContent LIKE %:keyword%")
    List<Info> findByInfoContentContaining(Room room, String keyword);
    List<Info> findByRoom(Room room);
}
