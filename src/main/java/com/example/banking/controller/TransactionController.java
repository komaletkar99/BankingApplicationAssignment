package com.example.banking.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.banking.dto.TransactionDTO;
import com.example.banking.service.AccountService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public void transferMoney(@RequestBody TransactionDTO transactionDTO) {
        accountService.transferMoney(transactionDTO);
    }
}
