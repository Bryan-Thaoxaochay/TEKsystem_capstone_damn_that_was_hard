package com.teksystems.capstone.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommentBean {
    private Integer id;
    private String comment;
    private String blogpostId;
}
