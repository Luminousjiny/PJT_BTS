package com.ssafy.bts.Repository;

import com.ssafy.bts.Domain.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserId(String userId);
    User findByUserIdAndUserPhone(String userId, String userPhone);
    User findByUserIdAndUserPw(String userId, String userPw);
    User findByUserPhone(String userPhone);

    @Query("select u from User u order by u.userPoint desc")
    List<User> findAwardList();
}
