package com.example.banking.service.iml;

import com.example.banking.dto.TransactionDto;
import com.example.banking.entity.Account;
import com.example.banking.entity.Transaction;
import com.example.banking.enums.TransactionType;
import com.example.banking.exception.BankingException;
import com.example.banking.mapper.TransactionMapper;
import com.example.banking.repository.AccountRepository;
import com.example.banking.repository.TransactionRepository;
import com.example.banking.service.TransactionService;
import com.example.banking.validators.TransactionValidator;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    private TransactionRepository transactionRepository;
    private AccountRepository accountRepository;
    private TransactionValidator transactionValidator;
    public TransactionServiceImpl(TransactionRepository transactionRepository, AccountRepository accountRepository, TransactionValidator transactionValidator) {
        this.transactionRepository=transactionRepository;
        this.accountRepository = accountRepository;
        this.transactionValidator = transactionValidator;

    }
    @Override
    public void createTransactionAndUpdateBalance(TransactionDto transactionDto) {
        // Create transaction
        createTransaction(transactionDto);

        // Update account balance
        updateAccountBalance(transactionDto);
    }
    public TransactionDto createTransaction(TransactionDto transactionDto) {
        // Perform validation before creating the transaction
        if (!transactionValidator.validateTransaction(transactionDto)) {
            throw new BankingException("Transaction validation failed during createTransaction operation");
        }

        Transaction transaction = TransactionMapper.mapToTransaction(transactionDto);
        Transaction savedTransaction = transactionRepository.save(transaction);
        return TransactionMapper.mapToTransactionDto(savedTransaction);
    }

    public void updateAccountBalance(TransactionDto transactionDto) {
        // Update account balance according to relevant transaction type
        if (transactionDto.getTransactionType() == TransactionType.DEPOSIT) {
            Account account = accountRepository.findById(transactionDto.getAccountId())
                    .orElseThrow(() -> new BankingException("Account not found for deposit transaction"));

            BigDecimal currentBalance = account.getBalance();
            BigDecimal newBalance = currentBalance.add(transactionDto.getAmount());
            account.setBalance(newBalance);
            accountRepository.save(account);
        } else if (transactionDto.getTransactionType() == TransactionType.WITHDRAWAL) {
            Account account = accountRepository.findById(transactionDto.getAccountId())
                    .orElseThrow(() -> new BankingException("Account not found for withdrawal transaction"));

            BigDecimal currentBalance = account.getBalance();
            BigDecimal newBalance = currentBalance.subtract(transactionDto.getAmount());

            account.setBalance(newBalance);
            accountRepository.save(account);
        }
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



}
