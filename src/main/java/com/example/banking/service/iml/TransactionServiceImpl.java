package com.example.banking.service.iml;

import com.example.banking.dto.TransactionDto;
import com.example.banking.entity.Account;
import com.example.banking.entity.Transaction;
import com.example.banking.enums.TransactionType;
import com.example.banking.mapper.TransactionMapper;
import com.example.banking.repository.AccountRepository;
import com.example.banking.repository.TransactionRepository;
import com.example.banking.service.TransactionService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    private TransactionRepository transactionRepository;
    private AccountRepository accountRepository;
    public TransactionServiceImpl(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository=transactionRepository;
        this.accountRepository = accountRepository;
    }

    public TransactionDto createTransaction(TransactionDto transactionDto) {
        Transaction transaction = TransactionMapper.mapToTransaction(transactionDto);
        Transaction savedTransaction = transactionRepository.save(transaction);
        return TransactionMapper.mapToTransactionDto(savedTransaction);

    }
    public TransactionDto getTransactionById(Long id){
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found with id: " + id));
        return TransactionMapper.mapToTransactionDto(transaction);
    }
    public List<TransactionDto> getTransactionAll(){
        List<Transaction> transactions = transactionRepository.findAll();
        List<TransactionDto> transactionDtos = new ArrayList<>();
        for (Transaction transaction:transactions) {
            transactionDtos.add(TransactionMapper.mapToTransactionDto(transaction));
        }
        return transactionDtos;
    }

    public void updateAccountBalance(TransactionDto transactionDto){
        // Update account balance according to relevant transaction type
        if (transactionDto.getTransactionType() == TransactionType.DEPOSIT) {
            Account account = accountRepository.findById(transactionDto.getAccountId()).orElse(null);
            System.out.println("account = " + account);
            if (account != null) {
                BigDecimal currentBalance = account.getBalance();
                BigDecimal newBalance = currentBalance.add(transactionDto.getAmount());
                System.out.println("currentBalance : " + currentBalance);
                System.out.println("newBalance : " + newBalance);
                account.setBalance(newBalance);
                accountRepository.save(account);
            }
        } else if (transactionDto.getTransactionType() == TransactionType.WITHDRAWAL) {
            Account account = accountRepository.findById(transactionDto.getAccountId()).orElse(null);
            if (account != null) {
                BigDecimal currentBalance = account.getBalance();
                BigDecimal newBalance = currentBalance.subtract(transactionDto.getAmount());
                account.setBalance(newBalance);
                accountRepository.save(account);
            }
        }
    }

}
