package com.yzengin.banking.controller;

import com.yzengin.banking.dto.TransactionDto;
import com.yzengin.banking.service.TransactionService;
import com.yzengin.banking.service.iml.TransactionServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    private TransactionService transactionService;
    private TransactionServiceImpl transactionServiceImpl;
    public TransactionController(TransactionService transactionService,TransactionServiceImpl transactionServiceImpl){
        this.transactionService = transactionService;
        this.transactionServiceImpl = transactionServiceImpl;
    }

    // REST API endpoint to get all transactions
    @GetMapping
    public ResponseEntity<List<TransactionDto>> getAllTransactions(){
        List<TransactionDto> transactionDtos = transactionService.getTransactionAll();
        return ResponseEntity.ok(transactionDtos);
    }

    // REST API endpoint to get a transaction by its ID
    @GetMapping("/{id}")
    public ResponseEntity<TransactionDto> getTransactionById(@PathVariable Long id) {
        TransactionDto transactionDto = transactionService.getTransactionById(id);
        return ResponseEntity.ok(transactionDto);
    }

    // REST API endpoint to create a new transaction
    @PostMapping
    public ResponseEntity<TransactionDto> createTransaction(@RequestBody TransactionDto transactionDto){
        transactionService.createTransactionAndUpdateBalance(transactionDto);
        return new ResponseEntity<>(transactionDto, HttpStatus.CREATED);
    }
}
