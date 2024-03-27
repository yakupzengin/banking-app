package com.example.banking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "account_id")
    private Long accountId;
    @Column(name = "transaction_type")
    private String transactionType;

    private BigDecimal amount;
    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;


    public Transaction(Long accountId, String transactionType, BigDecimal amount, LocalDateTime localDateTime) {
        this.accountId=accountId;
        this.transactionType=transactionType;
        this.amount=amount;
        this.transactionDate = LocalDateTime.now();
    }


}
