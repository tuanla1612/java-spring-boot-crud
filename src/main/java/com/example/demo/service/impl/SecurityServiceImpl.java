package com.example.demo.service.impl;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.Security;
import com.example.demo.repository.SecurityRepository;
import com.example.demo.service.SecurityService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SecurityServiceImpl implements SecurityService {
    SecurityRepository securityRepository;

    public SecurityServiceImpl(SecurityRepository securityRepository) {
        this.securityRepository = securityRepository;
    }

    @Override
    public String createSecurity(Security security) {
        UUID otpId = UUID.randomUUID();
        security.setOtpId(otpId.toString());
        securityRepository.save(security);
        return "Successfully created security";
    }

    @Override
    public String updateSecurity(Security security) {
        securityRepository.save(security);
        return "Successfully updated security";
    }

    @Override
    public String deleteSecurity(String otpId) {
        if (!securityRepository.existsById(otpId)) {
            throw new UserNotFoundException("Request security not found");
        }
        securityRepository.deleteById(otpId);
        return "";
    }

    @Override
    public Security getSecurityDetails(String otpId) {
        if (!securityRepository.existsById(otpId)) {
            throw new UserNotFoundException("Request security not found");
        }
        return securityRepository.findById(otpId).get();
    }

    @Override
    public List<Security> getAllSecuritys() {
        return securityRepository.findAll();
    }
}
