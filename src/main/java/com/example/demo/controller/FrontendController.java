package com.example.demo.controller;

import com.example.demo.service.TransactionService;
import com.example.demo.service.WalletService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class FrontendController {

    private final WalletService walletService;
    private final TransactionService transactionService;

    public FrontendController(WalletService walletService, TransactionService transactionService) {
        this.walletService = walletService;
        this.transactionService = transactionService;
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    @GetMapping("/profile")
    public String showProfilePage() {
        return "profile";
    }

    @GetMapping("/wallet-detail/{walletId}")
    public String showWalletPage(Model model, @PathVariable String walletId) {
        model.addAttribute("wallet", walletService.getWalletDetails(walletId));
        model.addAttribute("transactions", transactionService.getTransactionsByWalletId(walletId));
        return "wallet-detail";
    }
}
