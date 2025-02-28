package com.example.demo.controller;

import com.example.demo.model.Wallet;
import com.example.demo.response.ResponseHandler;
import com.example.demo.service.WalletService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/wallet")
public class WalletController {
    WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping("{walletId}")
    public ResponseEntity<Object> getWalletDetails(@PathVariable("walletId") String walletId)
    {
        return ResponseHandler.responseBuilder("Request wallet detail is here", HttpStatus.OK, walletService.getWalletDetails(walletId));
    }

    @GetMapping()
    public List<Wallet> getAllWallets()
    {
        return walletService.getAllWallets();
    }

    @GetMapping("/user/{userId}")
    public List<Wallet> getAllWallets(@PathVariable String userId) {
        return walletService.getWalletsByUserId(userId);
    }

    @PostMapping
    public String createWallet(@RequestBody Wallet wallet)
    {
        walletService.createWallet(wallet);
        return "Wallet created successfully";
    }

    @PutMapping
    public String updateWallet(@RequestBody Wallet wallet)
    {
        walletService.updateWallet(wallet);
        return "Wallet updated successfully";
    }

    @DeleteMapping("{walletId}")
    public String deleteWallet(@PathVariable("walletId") String walletId)
    {
        walletService.deleteWallet(walletId);
        return "Wallet deleted successfully";
    }
}
