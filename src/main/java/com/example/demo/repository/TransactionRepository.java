package com.example.demo.repository;

import com.example.demo.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, String>{
    public List<Transaction> findTransactionsByWalletId(String walletId);

    @Query("SELECT tr FROM Transaction tr WHERE tr.walletId = :walletId AND tr.status = \"PENDING\"")
    public List<Transaction> findPendingTransactionsByWalletId(String walletId);
}
