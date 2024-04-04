package com.yzengin.banking.entity;

import com.yzengin.banking.enums.TransactionType;
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

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "transaction_type")
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;


    private BigDecimal amount;
    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;


    public Transaction(Account account, TransactionType transactionType, BigDecimal amount, LocalDateTime localDateTime) {
        this.account=account;
        this.transactionType=transactionType;
        this.amount=amount;
        this.transactionDate = LocalDateTime.now();
    }


}
