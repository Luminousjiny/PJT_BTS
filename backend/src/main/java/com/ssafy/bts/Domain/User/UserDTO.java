package com.ssafy.bts.Domain.User;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "사용자 Dto", description = "반환시 User 객체를 넣을 경우 매핑된 데이터는 줄줄이 딸려오기 때문에 따로 만들어 줌")
public class UserDTO { // 반환에서 필요한 DTO
    private String userId;
    private String userPw;
    private String userNickname;
    private int userPhone;
    private String userImg;
    private String userLank;
    private int userPoint;

    public UserDTO(User user){
        this.userId = user.getUserId();
        this.userPw = user.getUserPw();
        this.userNickname = user.getUserNickname();
        this.userPhone = user.getUserPhone();
        this.userImg = user.getUserImg();
        this.userLank = user.getUserLank();
        this.userPoint = user.getUserPoint();

        if(user.getUserImg() != null){
            this.userImg = user.getUserImg();
        }
    }
}
