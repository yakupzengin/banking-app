package com.example.banking.dto;

import com.example.banking.enums.TransactionType;
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
    private TransactionType transactionType;

    private BigDecimal amount;
    private LocalDateTime transactionDate;
    public TransactionDto(Long accountId, TransactionType transactionType, BigDecimal amount) {
        this.accountId = accountId;
        this.transactionType = transactionType;
        this.amount = amount;
        this.transactionDate = LocalDateTime.now();
    }
}
