package com.teksystems.capstone.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SignUpBean {
    private Integer id;

    private String firstName;

    private String lastName;

    private String username;

    private String email;

    private String password;

    private String confirmPassword;


}
