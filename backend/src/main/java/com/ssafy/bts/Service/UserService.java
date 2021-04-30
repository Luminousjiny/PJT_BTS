package com.ssafy.bts.Service;

import com.ssafy.bts.Domain.User.User;
import com.ssafy.bts.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    /**
     * 아이디로 유저객체 찾기
     * @return
     */
    @Transactional
    public User findByUserId(String userId){
        return userRepository.findByUserId(userId);
    }
}
