package com.example.demo.service.impl;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.Transaction;
import com.example.demo.model.Wallet;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.service.TransactionService;
import com.example.demo.service.WalletService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionServiceImpl implements TransactionService {
    TransactionRepository transactionRepository;
    WalletService walletService;

    public TransactionServiceImpl(TransactionRepository transactionRepository, WalletService walletService) {
        this.transactionRepository = transactionRepository;
        this.walletService = walletService;
    }

    @Override
    public String createTransaction(Transaction transaction) {
        UUID transactionId = UUID.randomUUID();
        transaction.setTransactionId(transactionId.toString());
        transaction.setStatus(Transaction.TransactionStatus.PENDING);
        Wallet wallet = walletService.getWalletDetails(transaction.getWalletId());
        BigDecimal oldBalance = wallet.getBalance();
        Transaction.TransactionType type = transaction.getType();
        if (type == Transaction.TransactionType.WITHDRAW) {
            if (oldBalance.compareTo(transaction.getAmount()) < 0) {
                throw new RuntimeException("Not enough balance");
            }
        }
        transactionRepository.save(transaction);

        if (type == Transaction.TransactionType.WITHDRAW) {
            wallet.setBalance(oldBalance.subtract(transaction.getAmount()));
        }
        walletService.updateWallet(wallet);
        return "Successfully created transaction";
    }

    @Override
    public String updateTransaction(Transaction transaction) {
        Transaction.TransactionStatus status = transaction.getStatus();
        Transaction.TransactionType type = transaction.getType();
        if (status == Transaction.TransactionStatus.FAILED && type == Transaction.TransactionType.WITHDRAW
        || status == Transaction.TransactionStatus.COMPLETED && type == Transaction.TransactionType.DEPOSIT) {
            Wallet wallet = walletService.getWalletDetails(transaction.getWalletId());
            BigDecimal oldBalance = wallet.getBalance();
            wallet.setBalance(oldBalance.add(transaction.getAmount()));
            walletService.updateWallet(wallet);
        }
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

    @Override
    public List<Transaction> getTransactionsByWalletId(String walletId) {
        return transactionRepository.findTransactionsByWalletId(walletId);
    }
}
