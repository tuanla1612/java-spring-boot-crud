package com.example.demo.model;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.sql.Timestamp;

@Entity
@Table(name = "security_auth")
public class Security {
    public enum OtpStatus {
        UNUSED, USED, EXPIRED
    }

    @Id
    @UuidGenerator
    private String otpId;
    private String userId;
    private String otpCode;
    private Timestamp expiredAt;
    private OtpStatus status;

    public Security() {}

    public Security(String otpId, String userId, String otpCode, Timestamp expiredAt, OtpStatus status) {
        this.otpId = otpId;
        this.userId = userId;
        this.otpCode = otpCode;
        this.expiredAt = expiredAt;
        this.status = status;
    }

    public String getOtpId() {
        return otpId;
    }

    public void setOtpId(String otpId) {
        this.otpId = otpId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOtpCode() {
        return otpCode;
    }

    public void setOtpCode(String otpCode) {
        this.otpCode = otpCode;
    }

    public Timestamp getExpiredAt() {
        return expiredAt;
    }

    public void setExpiredAt(Timestamp expiredAt) {
        this.expiredAt = expiredAt;
    }

    public OtpStatus getStatus() {
        return status;
    }

    public void setStatus(OtpStatus status) {
        this.status = status;
    }
}
