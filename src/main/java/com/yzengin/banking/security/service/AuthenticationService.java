package com.yzengin.banking.security.service;

import com.yzengin.banking.entity.User;
import com.yzengin.banking.repository.UserRepository;
import com.yzengin.banking.security.dto.LoginDto;
import com.yzengin.banking.security.entity.Role;
import com.yzengin.banking.security.entity.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationService(PasswordEncoder passwordEncoder, UserRepository userRepository, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public Token register(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if(user.getRole() != Role.USER && user.getRole() != Role.ADMIN) user.setRole(Role.USER);
        user.setRole(user.getRole());
        userRepository.save(user);
        String token = jwtService.generateToken(user.getEmail());
        Token role = new Token();
        role.setToken(token);
        role.setRole(user.getRole());
        return role;
    }

    public Token login(LoginDto user){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getEmail(),
                        user.getPassword()
                )
        );
        User user1 = userRepository.findByEmail(user.getEmail()).orElseThrow();
        System.out.println("user1 : " + user1);
        System.out.println("user1.getRole() : " + user1.getRole());
        String token = jwtService.generateToken(user1.getEmail());
        Token role = new Token();
        role.setToken(token);
        role.setRole(user1.getRole());
        return role;
    }
}
