package com.yzengin.banking.service;

import com.yzengin.banking.dto.TransactionDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransactionService {

    List<TransactionDto> getTransactionAll();

    TransactionDto getTransactionById(Long id);

    TransactionDto createTransaction(TransactionDto transactionDto);

    void updateAccountBalance(TransactionDto transactionDto);

    void createTransactionAndUpdateBalance(TransactionDto transactionDto);
}
