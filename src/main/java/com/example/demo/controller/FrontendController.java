package com.example.demo.controller;

import com.example.demo.service.TransactionService;
import com.example.demo.service.TransferService;
import com.example.demo.service.WalletService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class FrontendController {

    private final WalletService walletService;
    private final TransactionService transactionService;
    private final TransferService transferService;

    public FrontendController(WalletService walletService, TransactionService transactionService, TransferService transferService) {
        this.walletService = walletService;
        this.transactionService = transactionService;
        this.transferService = transferService;
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
        model.addAttribute("transfers", transferService.getTransfersBySenderWallet(walletId));
        return "wallet-detail";
    }

    @GetMapping("/transfer-form")
    public String showTransferPage() {
        return "transfer";
    }

    @GetMapping("/transfer-success")
    public String showTransferSuccessPage() {
        return "transfer-success";
    }

    @GetMapping("/transaction-form")
    public String showTransactionPage() {
        return "transaction";
    }

}
