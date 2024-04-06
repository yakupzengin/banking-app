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

    // Endpoint to retrieve all users
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> userDtos = userService.getUserAll();
        return ResponseEntity.ok(userDtos);
    }

    // Endpoint to retrieve a specific user by id
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id){
        UserDto userDto = userService.getUserById(id);
        return ResponseEntity.ok(userDto);
    }

    // Endpoint to retrieve accounts of a specific user
    @GetMapping("/{id}/accounts")
    public ResponseEntity<List<AccountDto>> getUserAccounts(@PathVariable Long id) {
        List<AccountDto> accountDtos = userService.getUserAccounts(id);
        return ResponseEntity.ok(accountDtos);
    }

    // Endpoint to retrieve transactions of a specific user
    @GetMapping("/{id}/transactions")
    public ResponseEntity<List<TransactionDto>> getUserTransactions(@PathVariable Long id) {
        List<TransactionDto> transactionDtos = userService.getUserTransactions(id);
        return ResponseEntity.ok(transactionDtos);
    }

    // Endpoint to add a new user
    @PostMapping
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);
    }

    // Endpoint to add an account to a user
    @PostMapping("/{id}/accounts")
    public ResponseEntity<AccountDto> addAccountToUser(@PathVariable Long id, @RequestBody AccountDto accountDto) {
        return new ResponseEntity<>(userService.addAccountToUser(id, accountDto), HttpStatus.CREATED);
    }

    // Endpoint to add a transaction to a user
    @PostMapping("/{id}/transactions")
    public ResponseEntity<TransactionDto> addTransactionToUser(@PathVariable Long id, @RequestBody TransactionDto transactionDto) {
        return new ResponseEntity<>(userService.addTransactionToUser(id, transactionDto), HttpStatus.CREATED);
    }

    // Endpoint to update a user
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.updateUser(id, userDto));
    }

    // Endpoint to delete a user
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccountById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok("User is deleted succesfully!");
    }

}
