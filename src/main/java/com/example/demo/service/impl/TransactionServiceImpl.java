package com.example.demo.service.impl;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.Transaction;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.service.TransactionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TransactionServiceImpl implements TransactionService {
    TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public String createTransaction(Transaction transaction) {
        UUID transactionId = UUID.randomUUID();
        transaction.setTransactionId(transactionId.toString());
        transactionRepository.save(transaction);
        return "Successfully created transaction";
    }

    @Override
    public String updateTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
        return "Successfully updated transaction";
    }

    @Override
    public String deleteTransaction(String transactionId) {
        if (!transactionRepository.existsById(transactionId)) {
            throw new UserNotFoundException("Request transaction not found");
        }
        transactionRepository.deleteById(transactionId);
        return "";
    }

    @Override
    public Transaction getTransactionDetails(String transactionId) {
        if (!transactionRepository.existsById(transactionId)) {
            throw new UserNotFoundException("Request transaction not found");
        }
        return transactionRepository.findById(transactionId).get();
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
}
