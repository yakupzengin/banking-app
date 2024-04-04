package com.yzengin.banking.validators;


import com.yzengin.banking.dto.TransactionDto;
import com.yzengin.banking.entity.Account;
import com.yzengin.banking.enums.TransactionType;
import com.yzengin.banking.exception.BankingException;
import com.yzengin.banking.repository.AccountRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TransactionValidator {

    private AccountRepository accountRepository;

    public TransactionValidator(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public boolean validateTransaction(TransactionDto transactionDto ) {
        // Check if transaction type is valid
        if (transactionDto.getTransactionType() == null ||
                (transactionDto.getTransactionType() != TransactionType.DEPOSIT &&
                        transactionDto.getTransactionType() != TransactionType.WITHDRAWAL)) {
            throw new BankingException("Transaction validation failed during createTransaction operation");
        }

        // Check if transaction amount is negative
        if (transactionDto.getAmount().compareTo(BigDecimal.ZERO) <= 0){
            throw new BankingException("Transaction amount must be positive");
        }

        // Check if account exists
        Account account = accountRepository.findById(transactionDto.getAccountId())
                .orElseThrow(() -> new BankingException("Account not found"));

        // Check if withdrawal amount is greater than account balance
        if (transactionDto.getTransactionType() == TransactionType.WITHDRAWAL &&
                transactionDto.getAmount().compareTo(account.getBalance()) > 0 ){
            throw new BankingException("There is not enough money for withdrawal");
        }

        return true;
    }



}
