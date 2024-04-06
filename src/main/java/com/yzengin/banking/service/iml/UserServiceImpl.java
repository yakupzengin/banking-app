package com.yzengin.banking.service.iml;

import com.yzengin.banking.dto.AccountDto;
import com.yzengin.banking.dto.TransactionDto;
import com.yzengin.banking.dto.UserDto;
import com.yzengin.banking.entity.User;
import com.yzengin.banking.mapper.UserMapper;
import com.yzengin.banking.repository.UserRepository;
import com.yzengin.banking.service.UserService;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public List<UserDto> getUserAll() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        for (User user: users){
            userDtos.add(UserMapper.mapToAccountDto(user));
        }
        return userDtos;
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User does not exists."));
        return UserMapper.mapToAccountDto(user);
    }

    @Override
    public UserDto createAccount(UserDto userDto) {
        return null;
    }

    @Override
    public void deleteUserById(Long id) {

    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        return null;
    }

    @Override
    public List<AccountDto> getUserAccounts(Long id) {
        return null;
    }

    @Override
    public List<TransactionDto> getUserTransactions(Long id) {
        return null;
    }

    @Override
    public AccountDto addAccountToUser(Long id, AccountDto accountDto) {
        return null;
    }

    @Override
    public TransactionDto addTransactionToUser(Long id, TransactionDto transactionDto) {
        return null;
    }
}
