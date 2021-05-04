package com.ssafy.bts.Repository;

import com.ssafy.bts.Domain.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserId(String userId);
    User findByUserIdAndUserPhone(String userId, String userPhone);
    User findByUserIdAndUserPw(String userId, String userPw);
}
