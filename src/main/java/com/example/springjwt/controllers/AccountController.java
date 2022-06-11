package com.example.springjwt.controllers;
import com.example.springjwt.models.AppUser;
import com.example.springjwt.payload.request.RegisterPayload;
import com.example.springjwt.payload.response.MessageResponse;
import com.example.springjwt.repositories.AppUserRepository;
import com.example.springjwt.services.AccountService;
import com.example.springjwt.services.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    @Autowired
    private AccountServiceImpl accountService;
    @Autowired
    private AppUserRepository appUserRepository;
    @PostMapping("/register")
    public ResponseEntity<?> addNewUser(@RequestBody @Valid RegisterPayload registerPayload) {
        if(appUserRepository.existsByUsername(registerPayload.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already in use."));
        }
        if(appUserRepository.existsByEmail(registerPayload.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use."));
        }
        accountService.addNewUser(registerPayload);
        return ResponseEntity.ok(new MessageResponse("User registered successfully! Check your email to activate your account. :)"));
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAllUsers() {
        return ResponseEntity.ok(accountService.findAllUsers());
    }
}
