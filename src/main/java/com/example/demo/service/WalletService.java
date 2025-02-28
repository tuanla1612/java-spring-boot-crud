package com.example.demo.service;

import com.example.demo.model.Wallet;

import java.util.List;

public interface WalletService {
    public String createWallet(Wallet wallet);
    public String updateWallet(Wallet wallet);
    public String deleteWallet(String walletId);
    public Wallet getWalletDetails(String walletId);
    public List<Wallet> getAllWallets();
    public List<Wallet> getWalletsByUserId(String userId);
}
