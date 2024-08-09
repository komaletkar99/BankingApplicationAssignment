package com.example.banking;
import com.example.banking.dto.TransactionDTO;
import com.example.banking.model.Account;
import com.example.banking.model.Client;
import com.example.banking.repository.AccountRepository;
import com.example.banking.repository.ClientRepository;
import com.example.banking.repository.TransactionRepository;
import com.example.banking.service.AccountService;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

@SpringBootTest
public class BankingApplicationTests {

	@InjectMocks
    private AccountService accountService;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @Test
    public void testTransferMoney() {
        MockitoAnnotations.openMocks(this);

        Account fromAccount = new Account();
        fromAccount.setCurrentBalance(new BigDecimal("1000.00"));

        Account toAccount = new Account();
        toAccount.setCurrentBalance(new BigDecimal("500.00"));

        when(accountRepository.findById(1L)).thenReturn(java.util.Optional.of(fromAccount));
        when(accountRepository.findById(2L)).thenReturn(java.util.Optional.of(toAccount));

        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setFromAccountId(1L);
        transactionDTO.setToAccountId(2L);
        transactionDTO.setAmount(new BigDecimal("100.00"));

        accountService.transferMoney(transactionDTO);

        assertEquals(new BigDecimal("900.00"), fromAccount.getCurrentBalance());
        assertEquals(new BigDecimal("600.00"), toAccount.getCurrentBalance());

        verify(accountRepository, times(2)).save(any(Account.class));
    }
}