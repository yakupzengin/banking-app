package com.yzengin.banking.mapper;

import com.yzengin.banking.dto.AccountDto;
import com.yzengin.banking.dto.UserDto;
import com.yzengin.banking.entity.User;

import java.util.List;

public class UserMapper {

    public static User mapToUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setLastName(userDto.getLastName());
        user.setUserName(userDto.getUserName());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setPassword(userDto.getPassword());
        return user;
    }

    public static UserDto mapToUserDto(User savedUser) {
        UserDto userDto = new UserDto();
        userDto.setUserName(savedUser.getUserName());
        userDto.setLastName(savedUser.getLastName());
        userDto.setPassword(savedUser.getPassword());
        userDto.setEmail(savedUser.getEmail());
        userDto.setPhoneNumber(savedUser.getPhoneNumber());
        userDto.setUpdateAt(savedUser.getUpdatedAt());

        // Convert accounts field from List<Account> to List<Account Dto> from User class
        List<AccountDto> accountDtos = AccountMapper.mapToAccountDtoList(savedUser.getAccounts());
        userDto.setAccounts(accountDtos);
        return userDto;
    }
}
