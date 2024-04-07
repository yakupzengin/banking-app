package com.yzengin.banking.service;

import com.yzengin.banking.dto.AccountDto;
import com.yzengin.banking.dto.TransactionDto;
import com.yzengin.banking.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getUserAll();

    UserDto getUserById(Long id);

    UserDto createUser(UserDto userDto);
    void deleteUserById(Long id);

    UserDto updateUser(Long id, UserDto userDto);

    List<AccountDto> getUserAccounts(Long id);

    List<TransactionDto> getUserTransactions(Long id);

    AccountDto addAccountToUser(Long id, AccountDto accountDto);
}
