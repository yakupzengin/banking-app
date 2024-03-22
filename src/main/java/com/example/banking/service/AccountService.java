package com.example.banking.service;

import com.example.banking.dto.AccountDto;

public interface AccountService {

    AccountDto createAccount(AccountDto account);
    AccountDto getAccountById(Long id);
}
