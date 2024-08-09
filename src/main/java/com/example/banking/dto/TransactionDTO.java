package com.example.banking.dto;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionDTO {
    private Long fromAccountId;
    private Long toAccountId;
    private BigDecimal amount;
}

