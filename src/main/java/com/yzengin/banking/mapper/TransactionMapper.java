package com.yzengin.banking.mapper;

import com.yzengin.banking.dto.TransactionDto;
import com.yzengin.banking.entity.Account;
import com.yzengin.banking.entity.Transaction;

public class TransactionMapper {

    public static Transaction mapToTransaction(TransactionDto transactionDto) {
        Transaction transaction = new Transaction();
        transaction.setAccount(new Account(transactionDto.getAccountId())); // Set the Account using accountId
        transaction.setTransactionType(transactionDto.getTransactionType());
        transaction.setAmount(transactionDto.getAmount());
        transaction.setTransactionDate(transactionDto.getTransactionDate());
        transaction.setDescription(transactionDto.getDescription());
        return transaction;
    }

    public static TransactionDto mapToTransactionDto(Transaction transaction) {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setAccountId(transaction.getAccount().getId()); // Get the accountId from Account
        transactionDto.setTransactionType(transaction.getTransactionType());
        transactionDto.setAmount(transaction.getAmount());
        transactionDto.setTransactionDate(transaction.getTransactionDate());
        transactionDto.setDescription(transaction.getDescription());
        return transactionDto;
    }
}
