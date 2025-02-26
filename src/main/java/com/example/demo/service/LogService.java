package com.example.demo.service;

import com.example.demo.model.Log;

import java.util.List;

public interface LogService {
    public String createLog(Log log);
    public String updateLog(Log log);
    public String deleteLog(String logId);
    public Log getLogDetails(String logId);
    public List<Log> getAllLogs();
}
