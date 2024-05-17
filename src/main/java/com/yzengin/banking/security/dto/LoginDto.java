package com.yzengin.banking.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
    private String email;

    private String password;


    @Override
    public String toString() {
        return "LoginDto{" +
                "Email='" + email + '\'' +
                ", Password=" + password +
                '}';
    }
}
