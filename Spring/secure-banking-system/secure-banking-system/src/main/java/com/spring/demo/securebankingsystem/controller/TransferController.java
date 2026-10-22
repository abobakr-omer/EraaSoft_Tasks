package com.spring.demo.securebankingsystem.controller;

import com.spring.demo.securebankingsystem.dto.CreateTransferRequest;
import com.spring.demo.securebankingsystem.dto.TransferDto;
import com.spring.demo.securebankingsystem.service.TransferService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transfers")
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping
    public ResponseEntity<TransferDto> create(@Valid @RequestBody CreateTransferRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(transferService.create(request));
    }

    @GetMapping("/{id}")
    public TransferDto getById(@PathVariable Long id) {
        return transferService.getById(id);
    }

    @PostMapping("/{id}/approve")
    public TransferDto approve(@PathVariable Long id) {
        return transferService.approve(id);
    }
}
