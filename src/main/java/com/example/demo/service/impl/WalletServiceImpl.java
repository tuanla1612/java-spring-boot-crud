package com.example.demo.service.impl;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.Wallet;
import com.example.demo.repository.WalletRepository;
import com.example.demo.service.WalletService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class WalletServiceImpl implements WalletService {
    WalletRepository walletRepository;

    public WalletServiceImpl(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public String createWallet(Wallet wallet) {
        UUID walletId = UUID.randomUUID();
        wallet.setUserId(walletId.toString());
        walletRepository.save(wallet);
        return "Successfully created wallet";
    }

    @Override
    public String updateWallet(Wallet wallet) {
        walletRepository.save(wallet);
        return "Successfully updated wallet";
    }

    @Override
    public String deleteWallet(String walletId) {
        if (walletRepository.findById(walletId).isEmpty())
            throw new UserNotFoundException("Request wallet not found");
        walletRepository.deleteById(walletId);
        return "";
    }

    @Override
    public Wallet getWalletDetails(String walletId) {
        if (walletRepository.findById(walletId).isEmpty())
            throw new UserNotFoundException("Request wallet not found");
        return walletRepository.findById(walletId).get();
    }

    @Override
    public List<Wallet> getAllWallets() {
        return walletRepository.findAll();
    }
}
