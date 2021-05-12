package com.ssafy.bts.Domain.Qna;

import com.ssafy.bts.Domain.Comment.Comment;
import com.ssafy.bts.Domain.Comment.CommentDTO;
import com.ssafy.bts.Domain.Room.RoomDTO;
import com.ssafy.bts.Domain.User.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QnaDTO {
    private int qnaId;
    private RoomDTO room;
    private UserDTO user;
    private String qnaTitle;
    private String qnaContent;
    private Date qnaDate;
    private List<CommentDTO> commentDTOList;

    public QnaDTO(Qna qna){
        this.qnaId = qna.getQnaId();

        if(qna.getRoom() != null){
            room = new RoomDTO();
            this.room.setRoomId(qna.getRoom().getRoomId());
        }

        if(qna.getUser() != null){
            user = new UserDTO();
            this.user.setUserId(qna.getUser().getUserId());
            this.user.setUserNickname(qna.getUser().getUserNickname());
            this.user.setUserImg(qna.getUser().getUserImg());
        }
        this.qnaTitle = qna.getQnaTitle();
        this.qnaContent = qna.getQnaContent();
        this.qnaDate = qna.getQnaDate();

        if(qna.getComment() != null){
            List<CommentDTO> commentList = new ArrayList<>();
            for (Comment comment : qna.getComment()) {
                CommentDTO commentDTO = new CommentDTO(comment);
                commentList.add(commentDTO);
            }
            this.commentDTOList = commentList;
        }
    }

}
