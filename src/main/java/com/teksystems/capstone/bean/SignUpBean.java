package com.teksystems.capstone.bean;

import com.teksystems.capstone.validation.EmailUnique;
import com.teksystems.capstone.validation.UsernameUnique;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@ToString
public class SignUpBean {
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

    @NotBlank(message = "Password must not be empty.")
    @Length(min = 8, max = 20, message = "Password must be between 8-20 characters.")
    private String password;

    @NotBlank(message = "This field is empty.")
    private String confirmPassword;
}
