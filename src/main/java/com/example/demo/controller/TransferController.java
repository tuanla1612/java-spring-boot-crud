package com.example.demo.controller;

import com.example.demo.model.Transfer;
import com.example.demo.response.ResponseHandler;
import com.example.demo.service.TransferService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping( "/transfer")
public class TransferController {
    TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @GetMapping("{transferId}")
    public ResponseEntity<Object> getTransferDetails(@PathVariable("transferId") String transferId)
    {
        return ResponseHandler.responseBuilder("Request Transfer detail is here", HttpStatus.OK, transferService.getTransferDetails(transferId));
    }

    @GetMapping()
    public List<Transfer> getAllTransfers()
    {
        return transferService.getAllTransfers();
    }

    @PostMapping
    public ResponseEntity<Object> createTransfer(@RequestBody Transfer Transfer)
    {
        transferService.createTransfer(Transfer);
        return ResponseHandler.responseBuilder("Transfer created successfully", HttpStatus.OK, Optional.empty());
    }

    @PutMapping
    public String updateTransfer(@RequestBody Transfer Transfer)
    {
        transferService.updateTransfer(Transfer);
        return "Transfer updated successfully";
    }

    @DeleteMapping("{transferId}")
    public String deleteTransfer(@PathVariable("transferId") String transferId)
    {
        transferService.deleteTransfer(transferId);
        return "Transfer deleted successfully";
    }
}
