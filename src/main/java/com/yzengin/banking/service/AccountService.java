package com.yzengin.banking.service;

import com.yzengin.banking.dto.AccountDto;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {

    AccountDto createAccount(AccountDto account);
    AccountDto getAccountById(Long id);
    List<AccountDto> getAccountAll();
    AccountDto deposit(Long id, BigDecimal amount);
    AccountDto withdraw(Long id, BigDecimal amount);
    void deleteAccountById(Long id);
}
