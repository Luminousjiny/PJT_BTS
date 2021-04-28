package com.ssafy.bts.Domain.Qna;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QnaDTO {
    private Integer qnaId;
//    private User user;
    private String qnaTitle;
    private String qnaContent;
    private Date qnaDate;

    public QnaDTO(Qna qna){

    }

}
