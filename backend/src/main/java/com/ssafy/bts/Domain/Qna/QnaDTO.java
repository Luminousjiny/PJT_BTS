package com.ssafy.bts.Domain.Qna;

import com.ssafy.bts.Domain.User.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QnaDTO {
    private int qnaId;
    private UserDTO user;
    private String qnaTitle;
    private String qnaContent;
    private Date qnaDate;

    public QnaDTO(Qna qna){
        this.qnaId = qna.getQnaId();
        if(qna.getUser() != null){
            user = new UserDTO();
            this.user.setUserId(qna.getUser().getUserId());
            this.user.setUserNickname(qna.getUser().getUserNickname());
            this.user.setUserImg(qna.getUser().getUserImg());
        }
        this.qnaTitle = qna.getQnaTitle();
        this.qnaContent = qna.getQnaContent();
        this.qnaDate = qna.getQnaDate();
    }

}
