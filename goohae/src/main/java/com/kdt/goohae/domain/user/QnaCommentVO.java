package com.kdt.goohae.domain.user;


import lombok.Data;

@Data
public class QnaCommentVO {

    private int commentSeq;
    private int boardSeq;
    private String id;
    private String comment;

}
