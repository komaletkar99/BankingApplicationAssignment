package com.example.banking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.banking.dto.TransactionDTO;
import com.example.banking.exception.InsufficientBalanceException;
import com.example.banking.exception.ResourceNotFoundException;
import com.example.banking.model.Account;
import com.example.banking.model.Transaction;
import com.example.banking.repository.AccountRepository;
import com.example.banking.repository.TransactionRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Transactional
    public void transferMoney(TransactionDTO transactionDTO) {
        Account fromAccount = accountRepository.findById(transactionDTO.getFromAccountId())
            .orElseThrow(() -> new ResourceNotFoundException("From Account not found"));
        Account toAccount = accountRepository.findById(transactionDTO.getToAccountId())
            .orElseThrow(() -> new ResourceNotFoundException("To Account not found"));

        BigDecimal transferAmount = transactionDTO.getAmount();

        if (fromAccount.getCurrentBalance().compareTo(transferAmount) < 0) {
            throw new InsufficientBalanceException("Insufficient balance");
        }

        fromAccount.setCurrentBalance(fromAccount.getCurrentBalance().subtract(transferAmount));
        toAccount.setCurrentBalance(toAccount.getCurrentBalance().add(transferAmount));

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        Transaction transaction = new Transaction();
        transaction.setFromAccount(fromAccount);
        transaction.setToAccount(toAccount);
        transaction.setAmount(transferAmount);
        transaction.setTimestamp(LocalDateTime.now());

        transactionRepository.save(transaction);
    }
}
