package com.example.demo.service.impl;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.Log;
import com.example.demo.repository.LogRepository;
import com.example.demo.service.LogService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LogServiceImpl implements LogService {
    LogRepository logRepository;

    public LogServiceImpl(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Override
    public String createLog(Log log) {
        UUID logId = UUID.randomUUID();
        log.setLogId(logId.toString());
        logRepository.save(log);
        return "Successfully created log";
    }

    @Override
    public String updateLog(Log log) {
        logRepository.save(log);
        return "Successfully updated log";
    }

    @Override
    public String deleteLog(String otpId) {
        if (!logRepository.existsById(otpId)) {
            throw new UserNotFoundException("Request log not found");
        }
        logRepository.deleteById(otpId);
        return "";
    }

    @Override
    public Log getLogDetails(String otpId) {
        if (!logRepository.existsById(otpId)) {
            throw new UserNotFoundException("Request log not found");
        }
        return logRepository.findById(otpId).get();
    }

    @Override
    public List<Log> getAllLogs() {
        return logRepository.findAll();
    }
}
