package com.spring.demo.securebankingsystem.service;

import com.spring.demo.securebankingsystem.dto.CreateTransferRequest;
import com.spring.demo.securebankingsystem.dto.TransferDto;
import com.spring.demo.securebankingsystem.enums.AccountStatus;
import com.spring.demo.securebankingsystem.enums.TransferStatus;
import com.spring.demo.securebankingsystem.exception.ApiException;
import com.spring.demo.securebankingsystem.model.Account;
import com.spring.demo.securebankingsystem.model.Customer;
import com.spring.demo.securebankingsystem.model.Transfer;
import com.spring.demo.securebankingsystem.repo.AccountRepository;
import com.spring.demo.securebankingsystem.repo.CustomerRepository;
import com.spring.demo.securebankingsystem.repo.TransferRepository;
import com.spring.demo.securebankingsystem.security.SecurityUtils;
import com.spring.demo.securebankingsystem.util.DtoMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
public class TransferService {

    private final TransferRepository transferRepository;
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    public TransferService(TransferRepository transferRepository, AccountRepository accountRepository,
                           CustomerRepository customerRepository) {
        this.transferRepository = transferRepository;
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
    }

    @PreAuthorize("hasAuthority('TRANSFER_CREATE')")
    public TransferDto create(CreateTransferRequest request) {
        // Rule 4: amount must be greater than zero
        if (request.amount().compareTo(BigDecimal.ZERO) <= 0) {
            throw ApiException.badRequest("Amount must be greater than zero");
        }
        // Rule 5: source and destination cannot be the same
        if (request.fromAccountId().equals(request.toAccountId())) {
            throw ApiException.badRequest("Source and destination accounts must be different");
        }
        // Rule 1 + 2: both accounts must exist
        Account from = accountRepository.findById(request.fromAccountId())
                .orElseThrow(() -> ApiException.notFound("Source account not found"));
        Account to = accountRepository.findById(request.toAccountId())
                .orElseThrow(() -> ApiException.notFound("Destination account not found"));

        // Rule 7: the source account must belong to the logged-in user (ADMIN is the only exception)
        Long myId = SecurityUtils.currentUser().getId();
        if (!SecurityUtils.hasRole("ADMIN") && !from.getCustomer().getId().equals(myId)) {
            throw new AccessDeniedException("You can only transfer from your own accounts");
        }
        // Rule 3: both accounts must be ACTIVE
        if (from.getStatus() != AccountStatus.ACTIVE || to.getStatus() != AccountStatus.ACTIVE) {
            throw ApiException.badRequest("Both accounts must be ACTIVE");
        }
        // The app has no exchange rates, so both accounts must use the same currency
        if (!from.getCurrency().equals(to.getCurrency())) {
            throw ApiException.badRequest("Both accounts must have the same currency");
        }
        // Rule 6: not more than the available balance
        if (from.getBalance().compareTo(request.amount()) < 0) {
            throw ApiException.badRequest("Insufficient balance");
        }

        // The creator comes from the SecurityContext, never from the request body
        Customer creator = customerRepository.findById(myId)
                .orElseThrow(() -> ApiException.notFound("Customer not found"));

        Transfer transfer = new Transfer();
        transfer.setFromAccount(from);
        transfer.setToAccount(to);
        transfer.setAmount(request.amount());
        transfer.setCreatedBy(creator);
        transfer.setStatus(TransferStatus.PENDING);   // money does NOT move yet

        return DtoMapper.toDto(transferRepository.save(transfer));
    }

    @PreAuthorize("hasAuthority('TRANSFER_READ')")
    public TransferDto getById(Long id) {
        Transfer transfer = findTransfer(id);

        // Customers can only read their own transfers
        if (!SecurityUtils.isStaff() && !transfer.getCreatedBy().getId().equals(SecurityUtils.currentUser().getId())) {
            throw new AccessDeniedException("You can only access your own transfers");
        }
        return DtoMapper.toDto(transfer);
    }

    @PreAuthorize("hasAuthority('TRANSFER_APPROVE')")
    public TransferDto approve(Long id) {
        Transfer transfer = findTransfer(id);

        if (transfer.getStatus() != TransferStatus.PENDING) {
            throw ApiException.badRequest("Only PENDING transfers can be approved");
        }

        // Check again: things may have changed since the transfer was created
        Account from = transfer.getFromAccount();
        Account to = transfer.getToAccount();
        if (from.getStatus() != AccountStatus.ACTIVE || to.getStatus() != AccountStatus.ACTIVE) {
            throw ApiException.badRequest("Both accounts must be ACTIVE");
        }
        if (from.getBalance().compareTo(transfer.getAmount()) < 0) {
            throw ApiException.badRequest("Insufficient balance");
        }

        // PENDING -> APPROVED -> move the money -> COMPLETED
        transfer.setStatus(TransferStatus.APPROVED);
        from.setBalance(from.getBalance().subtract(transfer.getAmount()));
        to.setBalance(to.getBalance().add(transfer.getAmount()));
        transfer.setStatus(TransferStatus.COMPLETED);

        accountRepository.save(from);
        accountRepository.save(to);
        return DtoMapper.toDto(transferRepository.save(transfer));
    }

    private Transfer findTransfer(Long id) {
        return transferRepository.findById(id)
                .orElseThrow(() -> ApiException.notFound("Transfer " + id + " not found"));
    }
}
