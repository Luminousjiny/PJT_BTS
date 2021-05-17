package com.ssafy.bts.Service;

import com.ssafy.bts.Controller.Request.ChangePwRequest;
import com.ssafy.bts.Controller.Request.UserRequest;
import com.ssafy.bts.Domain.User.User;
import com.ssafy.bts.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    /**
     * 회원가입
     */
    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    /**
     * 유저 전체 리스트 반환
     */
    @Transactional
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * 아이디로 유저객체 찾기
     */
    @Transactional
    public User findByUserId(String userId){
        return userRepository.findByUserId(userId);
    }

    /**
     * 아이디로 중복확인
     */
    @Transactional
    public boolean checkUserId(String userId) {
        if(userRepository.findByUserId(userId)==null){ // null이면 중복X
            return false;
        }else{
            return true;
        }
    }

    /**
     * 유저 수정
     */
    @Transactional
    public void updateUser(String userId, UserRequest request) {
        Optional<User> findUser = Optional.ofNullable(userRepository.findByUserId(userId));
        if(findUser.isPresent()) { // 값이 있는지 확인 => .isPresent()
            findUser.get().setUserPw(request.getUserPw());
            findUser.get().setUserNickname(request.getUserNickname());
            findUser.get().setUserPhone(request.getUserPhone());
        }
        else{
            throw new IllegalStateException("잘못된 유저 아이디입니다.");
        }
    }

    /**
     * 비밀번호 수정
     */
    @Transactional
    public void updatePw(ChangePwRequest request) {
        Optional<User> findUser = Optional.ofNullable(userRepository.findByUserId(request.getUserId()));
        if(findUser.isPresent()){
            findUser.get().setUserPw(request.getUserPw());
        }
        else{
            throw new IllegalStateException("잘못된 유저 아이디입니다.");
        }
    }

    /**
     * 유저 삭제
     */
    @Transactional
    public void deleteUser(String userId) {
        Optional<User> deleteUser = Optional.ofNullable(userRepository.findByUserId(userId));
        if(deleteUser.isPresent()){ // 값이 있는지 확인 => .isPresent()
            userRepository.delete(deleteUser.get());
        }
    }

    /**
     * 비밀번호 변경 전 확인 사항
     * 아이디, 핸드폰번호로 유저 찾기
     */
    @Transactional
    public User findByUserIdAndUserPhone(String userId, String userPhone) {
        return userRepository.findByUserIdAndUserPhone(userId, userPhone);
    }

    /**
     * 하나의 번호로 하나의 유저만 가입
     * 핸드폰번호로 유저찾기
     */
    @Transactional
    public User findByUserPhone(String userPhone) {
        return userRepository.findByUserPhone(userPhone);
    }

    /**
     * 로그인 시 , 아이디 비밀번호로 유저 찾기
     */
    public User findByUserIdAndUserPw(String userId, String userPw) {
        return userRepository.findByUserIdAndUserPw(userId, userPw);
    }

    /**
     * 유저 포인트, 랭크 수정시 사용
     */
    public void updateUser(User user) {
        Optional<User> findUser = Optional.ofNullable(userRepository.findByUserId(user.getUserId()));
        if(findUser.isPresent()) { // 값이 있는지 확인 => .isPresent()
            findUser.get().setUserPoint(user.getUserPoint());
            findUser.get().setUserLank(user.getUserLank());
        }
        else{
            throw new IllegalStateException("잘못된 유저 아이디입니다.");
        }
    }

    /**
     * 명예의전당 포인트 내림차순 10명 조회
     */
    @Transactional
    public List<User> findAwardList() {
        List<User> userList = userRepository.findAwardList();
        List<User> top10List = new ArrayList<>();
        int cnt = 0;
        if(userList.size()>0){
            for(User u : userList){
                if(cnt>9) break;
                top10List.add(u);
                cnt++;
            }
        }
        return top10List;
    }

}
