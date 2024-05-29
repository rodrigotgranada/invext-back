package com.invext.distribuidor.controller;

import com.invext.distribuidor.model.SupportRequest;
import com.invext.distribuidor.service.SupportRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/support")
public class SupportRequestController {

    @Autowired
    private SupportRequestService supportRequestService;

    @PostMapping("/request")
    public ResponseEntity<?> createSupportRequest(@RequestBody SupportRequest supportRequest) {
        try {
            String message = supportRequestService.assignSupportRequest(supportRequest);
            return ResponseEntity.ok().body(Map.of("message", message));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("message", "Internal Server Error"));
        }
    }

    @GetMapping("/available-agents")
    public ResponseEntity<?> getAvailableAgents() {
        return ResponseEntity.ok().body(supportRequestService.getAvailableAgents());
    }

    @GetMapping("/agents-queue")
    public ResponseEntity<?> getAgentsQueue() {
        return ResponseEntity.ok().body(supportRequestService.getAgentsQueue());
    }

    @GetMapping("/request-queue")
    public ResponseEntity<?> getRequestQueue() {
        return ResponseEntity.ok().body(supportRequestService.getRequestQueue());
    }
}
