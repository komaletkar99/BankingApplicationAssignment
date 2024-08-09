package com.example.banking.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.banking.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}

