package com.example.demo.repository;

import com.example.demo.model.Security;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecurityRepository extends JpaRepository<Security, String> {
}
