package com.teksystems.capstone.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PostBean {
    private Integer id;

    private String topic;

    private String title;

    private String description;

    private Integer userId;
}
