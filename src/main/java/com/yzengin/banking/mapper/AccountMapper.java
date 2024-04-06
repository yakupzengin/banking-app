package com.yzengin.banking.mapper;

import com.yzengin.banking.dto.AccountDto;
import com.yzengin.banking.entity.Account;
import com.yzengin.banking.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class AccountMapper {
    public static Account mapToAccount(AccountDto accountDto) {
        Account account = new Account();
        account.setUser(new User(accountDto.getUserId()));
        account.setBalance(accountDto.getBalance());
        account.setCurrency(accountDto.getCurrency());
        account.setOpenedAt(accountDto.getOpenedAt());
        return account;
    }

    public static AccountDto mapToAccountDto(Account account) {
        AccountDto accountDto = new AccountDto();
        accountDto.setUserId(account.getUser().getId());
        accountDto.setBalance(account.getBalance());
        accountDto.setCurrency(account.getCurrency());
        accountDto.setOpenedAt(account.getOpenedAt());
        return accountDto;
    }
    public static List<AccountDto> mapToAccountDtoList(List<Account> accounts) {
        return accounts.stream()
                .map(AccountMapper::mapToAccountDto) // Convert each Account object to AccountDto
                .collect(Collectors.toList()); // Collect and return converted AccountDtos into a list
    }
    public static Account mapToAccountEntity(AccountDto accountDto) {
        Account account = new Account();
        account.setUser(new User(accountDto.getUserId()));
        account.setBalance(accountDto.getBalance());
        account.setCurrency(accountDto.getCurrency());
        account.setOpenedAt(accountDto.getOpenedAt());
        return account;
    }

}
