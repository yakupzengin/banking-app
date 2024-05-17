package com.yzengin.banking.security.auth;

import com.yzengin.banking.entity.User;
import com.yzengin.banking.security.dto.LoginDto;
import com.yzengin.banking.security.service.AuthenticationService;
import com.yzengin.banking.security.entity.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public Token register(@RequestBody User user){
        return authenticationService.register(user);
    }
    @PostMapping("/login")
    public Token login(@RequestBody LoginDto loginDto){
        System.out.println(loginDto);
        return authenticationService.login(loginDto);
    }


}
