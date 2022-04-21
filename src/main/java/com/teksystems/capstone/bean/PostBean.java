package com.teksystems.capstone.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class PostBean {
    private Integer id;

    @NotBlank(message = "Topic must not be empty.")
    private String topic;

    @NotBlank(message = "Title must not be empty.")
    private String title;

    @NotBlank(message = "Story must not be empty.")
    @Length(min = 100, max = 8000, message = "Story must be between 100-8000 characters.")
    private String description;

    private Integer userId;
}
