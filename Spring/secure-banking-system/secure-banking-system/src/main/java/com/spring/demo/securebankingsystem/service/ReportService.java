package com.spring.demo.securebankingsystem.service;

import com.spring.demo.securebankingsystem.dto.ReportDto;
import com.spring.demo.securebankingsystem.enums.TransferStatus;
import com.spring.demo.securebankingsystem.model.Account;
import com.spring.demo.securebankingsystem.repo.AccountRepository;
import com.spring.demo.securebankingsystem.repo.CustomerRepository;
import com.spring.demo.securebankingsystem.repo.TransferRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional(readOnly = true)
public class ReportService {

    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;
    private final TransferRepository transferRepository;

    public ReportService(CustomerRepository customerRepository, AccountRepository accountRepository,
                         TransferRepository transferRepository) {
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
        this.transferRepository = transferRepository;
    }

    @PreAuthorize("hasAuthority('REPORT_VIEW')")
    public ReportDto summary() {
        BigDecimal totalBalance = accountRepository.findAll().stream()
                .map(Account::getBalance)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        long pending = transferRepository.findAll().stream()
                .filter(transfer -> transfer.getStatus() == TransferStatus.PENDING)
                .count();

        return new ReportDto(customerRepository.count(), accountRepository.count(),
                transferRepository.count(), pending, totalBalance);
    }
}
