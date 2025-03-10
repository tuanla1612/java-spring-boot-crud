package com.example.demo.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "transactions")
public class Transaction {
    public enum TransactionType {
        DEPOSIT, WITHDRAW, TRANSFER
    }

    public enum TransactionStatus {
        PENDING, COMPLETED, FAILED
    }

    @Id
    @UuidGenerator
    private String transactionId;

    private String walletId;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    public Transaction() {}

    public Transaction(String transactionId, String walletId, TransactionType type, BigDecimal amount, TransactionStatus status) {
        this.transactionId = transactionId;
        this.walletId = walletId;
        this.type = type;
        this.amount = amount;
        this.status = status;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getWalletId() {
        return walletId;
    }

    public void setWalletId(String walletId) {
        this.walletId = walletId;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }
}
