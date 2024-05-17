package com.yzengin.banking.dto;

import com.yzengin.banking.security.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String name;
    private String lastName;
    private String userName;
    private List<AccountDto> accounts;
    private String password;
    private String email;
    private String phoneNumber;
    private LocalDateTime createdAt;
    private Role role;

    @Override
    public String toString() {
        return "UserDto{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", accounts=" + accounts +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", createdAt=" + createdAt +
                ", role=" + role +
                '}';
    }
}
