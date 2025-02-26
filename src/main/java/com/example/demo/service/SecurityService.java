package com.example.demo.service;

import com.example.demo.model.Security;

import java.util.List;

public interface SecurityService {
    public String createSecurity(Security security);
    public String updateSecurity(Security security);
    public String deleteSecurity(String otpId);
    public Security getSecurityDetails(String otpId);
    public List<Security> getAllSecuritys();
}
