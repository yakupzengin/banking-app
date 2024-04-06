package com.yzengin.banking.dto;

import com.yzengin.banking.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private Long userId;
    private BigDecimal balance;
    private String currency;
    private LocalDateTime openedAt;
}
