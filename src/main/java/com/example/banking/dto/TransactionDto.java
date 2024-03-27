package com.example.banking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {
    private Long accountId;
    private String transactionType;
    private BigDecimal amount;
    private LocalDateTime transactionDate;
    public TransactionDto(Long accountId, String transactionType, BigDecimal amount) {
        this.accountId = accountId;
        this.transactionType = transactionType;
        this.amount = amount;
        this.transactionDate = LocalDateTime.now();
    }
}
