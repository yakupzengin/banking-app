package com.example.banking.mapper;

import com.example.banking.dto.TransactionDto;
import com.example.banking.entity.Transaction;

public class TransactionMapper {
    public static Transaction mapToTransaction(TransactionDto transactionDto) {
        return new Transaction(
                transactionDto.getAccountId(),
                transactionDto.getTransactionType(),
                transactionDto.getAmount(),
                transactionDto.getTransactionDate()
        );
    }

    public static TransactionDto mapToTransactionDto(Transaction transaction) {
        return new TransactionDto(
                transaction.getAccountId(),
                transaction.getTransactionType(),
                transaction.getAmount(),
                transaction.getTransactionDate()
        );
    }
}
