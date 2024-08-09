package com.example.banking.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal initialBalance;

    private BigDecimal currentBalance;

    @OneToOne(mappedBy = "account")
    private Client client;

    public void updateBalance(BigDecimal amount) {
        this.currentBalance = this.currentBalance.add(amount);
    }

    public void increaseBalance(BigDecimal interest) {
        this.currentBalance = this.currentBalance.add(this.currentBalance.multiply(interest));
        BigDecimal maxBalance = this.initialBalance.multiply(new BigDecimal("2.07"));
        if (this.currentBalance.compareTo(maxBalance) > 0) {
            this.currentBalance = maxBalance;
        }
    }
}
