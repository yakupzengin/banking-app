package com.example.banking.service.iml;

import com.example.banking.dto.AccountDto;
import com.example.banking.entity.Account;
import com.example.banking.mapper.AccountMapper;
import com.example.banking.repository.AccountRepository;
import com.example.banking.service.AccountService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;


    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account does not exists."));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public List<AccountDto> getAccountAll() {
        List<Account> accounts = accountRepository.findAll();
            List<AccountDto> accountDtos = new ArrayList<>();
        for (Account account: accounts){
            accountDtos.add(AccountMapper.mapToAccountDto(account));
        }
        return accountDtos;
    }

    @Override
    public AccountDto deposit(Long id, BigDecimal amount) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account does not exists."));
        BigDecimal total = account.getBalance().add(amount);
        account.setBalance(total);
        Account saveAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(saveAccount);
    }

    @Override
    public AccountDto withdraw(Long id, BigDecimal amount) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account does not exists."));

        if (account.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient amount");
        }

        BigDecimal total = account.getBalance().subtract(amount);
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);

        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public void deleteAccountById(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account does not exists."));
        accountRepository.deleteById(id);
    }
}
