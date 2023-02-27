package com.blog.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserRequestDto {

    @NotEmpty(message = "\"Name\" field must not be empty")
    @Size(min = 2, message = "\"Name\" field should be don't less 2 characters long")
    private String userName;

    @Email(message = "\"Email\" field should be valid")
    @NotBlank(message = "\"Email\" field must not be empty")
    private String email;

    @NotBlank(message = "\"Password\" field must not be empty")
    @Size(min = 8, message = "\"Password\" field should be don't less 8 characters long")
    private String password;

    public UserRequestDto() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}