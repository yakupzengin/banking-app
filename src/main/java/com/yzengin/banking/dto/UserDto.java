package com.yzengin.banking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
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
}
