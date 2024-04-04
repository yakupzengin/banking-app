package com.yzengin.banking.mapper;

import com.yzengin.banking.dto.TransactionDto;
import com.yzengin.banking.entity.Account;
import com.yzengin.banking.entity.Transaction;

public class TransactionMapper {
    public static Transaction mapToTransaction(TransactionDto transactionDto) {
        return new Transaction(
                // Creating an Account object and assigning it to the Transaction entity
                new Account(transactionDto.getAccountId()),
                transactionDto.getTransactionType(),
                transactionDto.getAmount(),
                transactionDto.getTransactionDate()
        );
    }

    public static TransactionDto mapToTransactionDto(Transaction transaction) {
        return new TransactionDto(
                // Retrieving Account ID from the Transaction entity
                transaction.getAccount().getId(),
                transaction.getTransactionType(),
                transaction.getAmount(),
                transaction.getTransactionDate()
        );
    }
}
