package com.yzengin.banking.controller;

import com.yzengin.banking.dto.AccountDto;
import com.yzengin.banking.dto.TransactionDto;
import com.yzengin.banking.dto.UserDto;
import com.yzengin.banking.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private UserService userService;

    public UserController (UserService userService){
        this.userService= userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> userDtos = userService.getUserAll();
        return ResponseEntity.ok(userDtos);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id){
        UserDto userDto = userService.getUserById(id);
        return ResponseEntity.ok(userDto);
    }
    @GetMapping("/{id}/accounts")
    public ResponseEntity<List<AccountDto>> getUserAccounts(@PathVariable Long id) {
        List<AccountDto> accountDtos = userService.getUserAccounts(id);
        return ResponseEntity.ok(accountDtos);
    }
    @GetMapping("/{id}/transactions")
    public ResponseEntity<List<TransactionDto>> getUserTransactions(@PathVariable Long id) {
        List<TransactionDto> transactionDtos = userService.getUserTransactions(id);
        return ResponseEntity.ok(transactionDtos);
    }

    @PostMapping
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.createAccount(userDto), HttpStatus.CREATED);
    }
    @PostMapping("/{id}/accounts")
    public ResponseEntity<AccountDto> addAccountToUser(@PathVariable Long id, @RequestBody AccountDto accountDto) {
        return new ResponseEntity<>(userService.addAccountToUser(id, accountDto), HttpStatus.CREATED);
    }
    @PostMapping("/{id}/transactions")
    public ResponseEntity<TransactionDto> addTransactionToUser(@PathVariable Long id, @RequestBody TransactionDto transactionDto) {
        return new ResponseEntity<>(userService.addTransactionToUser(id, transactionDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.updateUser(id, userDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccountById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok("User is deleted succesfully!");
    }

}
