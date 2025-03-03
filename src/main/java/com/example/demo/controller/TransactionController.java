package com.example.demo.controller;

import com.example.demo.model.Transaction;
import com.example.demo.response.ResponseHandler;
import com.example.demo.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping( "/transaction")
public class TransactionController {
    TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("{transactionId}")
    public ResponseEntity<Object> getTransactionDetails(@PathVariable("transactionId") String transactionId)
    {
        return ResponseHandler.responseBuilder("Request transaction detail is here", HttpStatus.OK, transactionService.getTransactionDetails(transactionId));
    }

    @GetMapping()
    public List<Transaction> getAllTransactions()
    {
        return transactionService.getAllTransactions();
    }

    @PostMapping
    public ResponseEntity<Object> createTransaction(@RequestBody Transaction transaction)
    {
        transactionService.createTransaction(transaction);
        return ResponseHandler.responseBuilder("Transaction created successfully", HttpStatus.OK, Optional.empty());
    }

    @PutMapping
    public String updateTransaction(@RequestBody Transaction transaction)
    {
        transactionService.updateTransaction(transaction);
        return "Transaction updated successfully";
    }

    @DeleteMapping("{transactionId}")
    public String deleteTransaction(@PathVariable("transactionId") String transactionId)
    {
        transactionService.deleteTransaction(transactionId);
        return "Transaction deleted successfully";
    }
}
