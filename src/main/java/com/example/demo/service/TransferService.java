package com.example.demo.service;

import com.example.demo.model.Transfer;

import java.util.List;

public interface TransferService {
    public String createTransfer(Transfer transfer);
    public String updateTransfer(Transfer transfer);
    public String deleteTransfer(String transferId);
    public Transfer getTransferDetails(String transferId);
    public List<Transfer> getAllTransfers();
}
