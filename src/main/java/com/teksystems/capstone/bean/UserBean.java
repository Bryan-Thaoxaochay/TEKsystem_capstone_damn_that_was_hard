package com.teksystems.capstone.bean;

import com.teksystems.capstone.validation.EmailUnique;
import com.teksystems.capstone.validation.UsernameUnique;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@ToString
public class UserBean {
    private Integer id;

    @NotBlank(message = "First name must not be empty.")
    private String firstName;

    @NotBlank(message = "Last name must not be empty.")
    private String lastName;

    @NotBlank(message = "Username must not be empty.")
    @UsernameUnique(message = "Username is taken.")
    private String username;

    @NotBlank(message = "Email must not be empty.")
    @Pattern(regexp = "[a-z0-9]+@[a-z]+\\.[a-z]{2,3}", message = "Invalid email")
    @EmailUnique(message = "Email is taken.")
    private String email;
}
