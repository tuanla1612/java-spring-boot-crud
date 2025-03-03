package com.example.demo.repository;

import com.example.demo.model.Transaction;
import com.example.demo.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransferRepository extends JpaRepository<Transfer, String> {
    public List<Transfer> findTransferBySenderWallet(String senderWallet);
}
