package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "transfers")
public class Transfer {
    @Id
    private String transferId;
    private String senderWallet;
    private String receiverWallet;
    private BigDecimal amount;

    private Transaction.TransactionStatus status;

    @CreationTimestamp
    private Timestamp createdAt;

    public Transfer() {}

    public Transfer(String transferId, String senderWallet, String receiverWallet, BigDecimal amount, Transaction.TransactionStatus status) {
        this.transferId = transferId;
        this.senderWallet = senderWallet;
        this.receiverWallet = receiverWallet;
        this.amount = amount;
        this.status = status;
    }

    public String getTransferId() {
        return transferId;
    }

    public void setTransferId(String transferId) {
        this.transferId = transferId;
    }

    public String getSenderWallet() {
        return senderWallet;
    }

    public void setSenderWallet(String senderWallet) {
        this.senderWallet = senderWallet;
    }

    public String getReceiverWallet() {
        return receiverWallet;
    }

    public void setReceiverWallet(String receiverWallet) {
        this.receiverWallet = receiverWallet;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Transaction.TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(Transaction.TransactionStatus status) {
        this.status = status;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }
}
