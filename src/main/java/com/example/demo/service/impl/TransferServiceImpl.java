package com.example.demo.service.impl;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.Transfer;
import com.example.demo.repository.TransferRepository;
import com.example.demo.service.TransferService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransferServiceImpl implements TransferService {
    TransferRepository transferRepository;

    public TransferServiceImpl(TransferRepository transferRepository) {
        this.transferRepository = transferRepository;
    }

    @Override
    public String createTransfer(Transfer transfer) {
        transferRepository.save(transfer);
        return "Successfully created transfer";
    }

    @Override
    public String updateTransfer(Transfer transfer) {
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
}
