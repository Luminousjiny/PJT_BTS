package com.ssafy.bts.Repository;

import com.ssafy.bts.Domain.Info.Info;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InfoRepository extends JpaRepository<Info, Long> {
    Info findByInfoId(int infoId);
    List<Info> findByInfoTitleContaining(String keyword); //Like검색 %{keyword}%
    List<Info> findByInfoContentContaining(String keyword);
}
