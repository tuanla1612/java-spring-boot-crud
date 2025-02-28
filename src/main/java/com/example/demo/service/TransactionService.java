package com.example.demo.service;

import com.example.demo.model.Transaction;

import java.util.List;

public interface TransactionService {
    public String createTransaction(Transaction transaction);
    public String updateTransaction(Transaction transaction);
    public String deleteTransaction(String transactionId);
    public Transaction getTransactionDetails(String transactionId);
    public List<Transaction> getAllTransactions();
    public List<Transaction> getTransactionsByWalletId(String walletId);
}
