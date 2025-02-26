package com.example.demo.controller;

import com.example.demo.model.Security;
import com.example.demo.response.ResponseHandler;
import com.example.demo.service.SecurityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/security")
public class SecurityController {
    SecurityService securityService;

    public SecurityController(SecurityService securityService) {
        this.securityService = securityService;
    }

    @GetMapping("{securityId}")
    public ResponseEntity<Object> getSecurityDetails(@PathVariable("securityId") String securityId)
    {
        return ResponseHandler.responseBuilder("Request Security detail is here", HttpStatus.OK, securityService.getSecurityDetails(securityId));
    }

    @GetMapping()
    public List<Security> getAllSecuritys()
    {
        return securityService.getAllSecuritys();
    }

    @PostMapping
    public String createSecurity(@RequestBody Security Security)
    {
        securityService.createSecurity(Security);
        return "Security created successfully";
    }

    @PutMapping
    public String updateSecurity(@RequestBody Security Security)
    {
        securityService.updateSecurity(Security);
        return "Security updated successfully";
    }

    @DeleteMapping("{securityId}")
    public String deleteSecurity(@PathVariable("securityId") String securityId)
    {
        securityService.deleteSecurity(securityId);
        return "Security deleted successfully";
    }
}
