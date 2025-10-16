package com.ui.demo.dto;

import lombok.Data;

@Data
public class UserSignUpDto {
    private String name;
    private String email;
    private String password;
    private String confirmPassword;
    private int countryCode;
    private String phoneNumber;
}
