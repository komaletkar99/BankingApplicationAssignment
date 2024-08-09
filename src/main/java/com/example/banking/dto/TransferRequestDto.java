package com.example.banking.dto;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class TransferRequestDto {

    @NotNull
    private Long recipientAccountId;

    @DecimalMin(value = "0.01")
    @NotNull
    private BigDecimal amount;

    // Getters and Setters
    public Long getRecipientAccountId() {
        return recipientAccountId;
    }

    public void setRecipientAccountId(Long recipientAccountId) {
        this.recipientAccountId = recipientAccountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}

