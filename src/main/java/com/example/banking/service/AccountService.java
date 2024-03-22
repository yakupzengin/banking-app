package com.example.banking.service;

import com.example.banking.dto.AccountDto;

import java.util.List;

public interface AccountService {

    AccountDto createAccount(AccountDto account);
    AccountDto getAccountById(Long id);
    List<AccountDto> getAccountAll();
    AccountDto deposit(Long id, double amount);
    AccountDto withdraw(Long id, double amount);
    void deleteAccountById(Long id);
}
