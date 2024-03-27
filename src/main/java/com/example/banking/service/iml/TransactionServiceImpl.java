package com.example.banking.service.iml;

import com.example.banking.dto.TransactionDto;
import com.example.banking.entity.Transaction;
import com.example.banking.mapper.TransactionMapper;
import com.example.banking.repository.TransactionRepository;
import com.example.banking.service.TransactionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    private TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository=transactionRepository;
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

}
