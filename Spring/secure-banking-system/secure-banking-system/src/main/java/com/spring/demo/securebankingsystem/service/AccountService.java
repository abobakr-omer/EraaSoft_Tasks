package com.spring.demo.securebankingsystem.service;

import com.spring.demo.securebankingsystem.dto.AccountDto;
import com.spring.demo.securebankingsystem.dto.CreateAccountRequest;
import com.spring.demo.securebankingsystem.dto.UpdateAccountRequest;
import com.spring.demo.securebankingsystem.exception.ApiException;
import com.spring.demo.securebankingsystem.model.Account;
import com.spring.demo.securebankingsystem.model.Customer;
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
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@Transactional
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final TransferRepository transferRepository;

    public AccountService(AccountRepository accountRepository, CustomerRepository customerRepository,
                          TransferRepository transferRepository) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
        this.transferRepository = transferRepository;
    }

    @PreAuthorize("hasAuthority('ACCOUNT_CREATE')")
    public AccountDto create(CreateAccountRequest request) {
        Customer owner = customerRepository.findById(request.customerId())
                .orElseThrow(() -> ApiException.notFound("Customer " + request.customerId() + " not found"));

        Account account = new Account();
        account.setAccountNumber(generateAccountNumber());
        account.setCurrency(request.currency().toUpperCase());
        account.setBalance(request.initialBalance() == null ? BigDecimal.ZERO : request.initialBalance());
        account.setCustomer(owner);
        return DtoMapper.toDto(accountRepository.save(account));
    }

    @PreAuthorize("hasAuthority('ACCOUNT_READ')")
    public AccountDto getById(Long id) {
        Account account = findAccount(id);

        // Ownership check: a normal customer can only read their own account (403 otherwise).
        if (!SecurityUtils.isStaff() && !account.getCustomer().getId().equals(SecurityUtils.currentUser().getId())) {
            throw new AccessDeniedException("You can only access your own accounts");
        }
        return DtoMapper.toDto(account);
    }

    @PreAuthorize("hasAuthority('ACCOUNT_READ')")
    public List<AccountDto> getMyAccounts() {
        // The id comes from the SecurityContext, so nobody can ask for someone else's accounts.
        Long myId = SecurityUtils.currentUser().getId();
        return accountRepository.findByCustomerId(myId).stream().map(DtoMapper::toDto).toList();
    }

    @PreAuthorize("hasAuthority('ACCOUNT_UPDATE')")
    public AccountDto update(Long id, UpdateAccountRequest request) {
        Account account = findAccount(id);
        account.setStatus(request.status());
        return DtoMapper.toDto(accountRepository.save(account));
    }

    @PreAuthorize("hasAuthority('ACCOUNT_DELETE')")
    public void delete(Long id) {
        Account account = findAccount(id);

        boolean hasTransfers = !transferRepository.findByFromAccountId(id).isEmpty()
                || !transferRepository.findByToAccountId(id).isEmpty();
        if (hasTransfers) {
            throw ApiException.badRequest("Account has transfers. Set its status to CLOSED instead of deleting it");
        }
        accountRepository.delete(account);
    }

    private Account findAccount(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> ApiException.notFound("Account " + id + " not found"));
    }

    private String generateAccountNumber() {
        String number;
        do {
            number = "ACC" + ThreadLocalRandom.current().nextLong(1_000_000_000L, 10_000_000_000L);
        } while (accountRepository.existsByAccountNumber(number));
        return number;
    }
}
