package com.example.demo.controller;

import com.example.demo.model.Log;
import com.example.demo.response.ResponseHandler;
import com.example.demo.service.LogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/log")
public class LogController {
    LogService logService;

    public LogController(LogService logService) {
        this.logService = logService;
    }

    @GetMapping("{logId}")
    public ResponseEntity<Object> getLogDetails(@PathVariable("logId") String logId)
    {
        return ResponseHandler.responseBuilder("Request Log detail is here", HttpStatus.OK, logService.getLogDetails(logId));
    }

    @GetMapping()
    public List<Log> getAllLogs()
    {
        return logService.getAllLogs();
    }

    @PostMapping
    public String createLog(@RequestBody Log Log)
    {
        logService.createLog(Log);
        return "Log created successfully";
    }

    @PutMapping
    public String updateLog(@RequestBody Log Log)
    {
        logService.updateLog(Log);
        return "Log updated successfully";
    }

    @DeleteMapping("{logId}")
    public String deleteLog(@PathVariable("logId") String logId)
    {
        logService.deleteLog(logId);
        return "Log deleted successfully";
    }
}
