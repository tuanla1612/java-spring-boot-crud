package com.example.demo.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.sql.Timestamp;

@Entity
@Table(name = "logs")
public class Log {
    @Id
    @UuidGenerator
    private String logId;
    private String userId;
    private String action;

    @CreationTimestamp
    private Timestamp createdAt;

    public Log() {}

    public Log(String logId, String userId, String action) {
        this.logId = logId;
        this.userId = userId;
        this.action = action;
    }

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }
}
