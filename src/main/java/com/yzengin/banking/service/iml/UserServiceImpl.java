package com.yzengin.banking.service.iml;

import com.yzengin.banking.dto.AccountDto;
import com.yzengin.banking.dto.TransactionDto;
import com.yzengin.banking.dto.UserDto;
import com.yzengin.banking.entity.Account;
import com.yzengin.banking.entity.Transaction;
import com.yzengin.banking.entity.User;
import com.yzengin.banking.mapper.AccountMapper;
import com.yzengin.banking.mapper.TransactionMapper;
import com.yzengin.banking.mapper.UserMapper;
import com.yzengin.banking.repository.AccountRepository;
import com.yzengin.banking.repository.TransactionRepository;
import com.yzengin.banking.repository.UserRepository;
import com.yzengin.banking.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private TransactionRepository transactionRepository;
    private AccountRepository accountRepository;
    public UserServiceImpl(UserRepository userRepository,TransactionRepository transactionRepository,AccountRepository accountRepository){
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }
    @Override
    public List<UserDto> getUserAll() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        for (User user: users){
            userDtos.add(UserMapper.mapToUserDto(user));
        }
        return userDtos;
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User does not exists."));
        return UserMapper.mapToUserDto(user);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = UserMapper.mapToUser(userDto);
        User savedUser = userRepository.save(user);
        return UserMapper.mapToUserDto(savedUser);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User does not exist."));
        user.setName(userDto.getName());
        user.setLastName(userDto.getLastName());
        user.setUserName(userDto.getUserName());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setPassword(userDto.getPassword());
        userRepository.save(user);
        return UserMapper.mapToUserDto(user);
    }

    @Override
    public List<AccountDto> getUserAccounts(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User does not exist."));
        List<Account> accounts = user.getAccounts();
        List<AccountDto> accountDtos = AccountMapper.mapToAccountDtoList(accounts);
        return accountDtos;
    }

    @Override
    public List<TransactionDto> getUserTransactions(Long id) {
        // Pull the transactions of the relevant user from the database
        List<Transaction> transactions = transactionRepository.findByAccountId(id);

        // Convert Transaction objects to TransactionDto and return
        return transactions.stream()
                .map(TransactionMapper::mapToTransactionDto)
                .collect(Collectors.toList());
    }

    @Override
    public AccountDto addAccountToUser(Long id, AccountDto accountDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User does not exist."));
        Account account = AccountMapper.mapToAccountEntity(accountDto);
        account.setUser(user);
        accountRepository.save(account);
        return AccountMapper.mapToAccountDto(account);
    }
}
