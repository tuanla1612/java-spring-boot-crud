package com.example.demo.service.impl;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.Transaction;
import com.example.demo.model.Transfer;
import com.example.demo.model.Wallet;
import com.example.demo.repository.TransferRepository;
import com.example.demo.service.TransferService;
import com.example.demo.service.WalletService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class TransferServiceImpl implements TransferService {
    TransferRepository transferRepository;
    WalletService walletService;

    public TransferServiceImpl(TransferRepository transferRepository,WalletService walletService) {
        this.transferRepository = transferRepository;
        this.walletService = walletService;
    }

    @Override
    public String createTransfer(Transfer transfer) {
        UUID transferId = UUID.randomUUID();
        transfer.setTransferId(transferId.toString());
        transfer.setStatus(Transaction.TransactionStatus.PENDING);
        Wallet wallet = walletService.getWalletDetails(transfer.getSenderWallet());
        BigDecimal oldBalance = wallet.getBalance();
        if (oldBalance.compareTo(transfer.getAmount()) < 0) {
            throw new RuntimeException("Not enough balance");
        }
        transferRepository.save(transfer);
        wallet.setBalance(oldBalance.subtract(transfer.getAmount()));
        walletService.updateWallet(wallet);
        return "Successfully created transfer";
    }

    @Override
    public String updateTransfer(Transfer transfer) {
        Transaction.TransactionStatus status = transfer.getStatus();
        if (status == Transaction.TransactionStatus.FAILED) {
            Wallet wallet = walletService.getWalletDetails(transfer.getSenderWallet());
            BigDecimal oldBalance = wallet.getBalance();
            wallet.setBalance(oldBalance.add(transfer.getAmount()));
            walletService.updateWallet(wallet);
        } else if (status == Transaction.TransactionStatus.COMPLETED) {
            Wallet wallet = walletService.getWalletDetails(transfer.getReceiverWallet());
            BigDecimal oldBalance = wallet.getBalance();
            wallet.setBalance(oldBalance.add(transfer.getAmount()));
            walletService.updateWallet(wallet);
        }
        transferRepository.save(transfer);
        return "Successfully updated transfer";
    }

    @Override
    public String deleteTransfer(String transferId) {
        if (!transferRepository.existsById(transferId)) {
            throw new UserNotFoundException("Request transfer not found");
        }
        transferRepository.deleteById(transferId);
        return "";
    }

    @Override
    public Transfer getTransferDetails(String transferId) {
        if (!transferRepository.existsById(transferId)) {
            throw new UserNotFoundException("Request transfer not found");
        }
        return transferRepository.findById(transferId).get();
    }

    @Override
    public List<Transfer> getAllTransfers() {
        return transferRepository.findAll();
    }

    @Override
    public List<Transfer> getTransfersBySenderWallet(String senderWalletId) {
        return transferRepository.findTransferBySenderWallet(senderWalletId);
    }
}
