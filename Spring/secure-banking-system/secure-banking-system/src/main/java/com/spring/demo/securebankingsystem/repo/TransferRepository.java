package com.spring.demo.securebankingsystem.repo;

import com.spring.demo.securebankingsystem.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransferRepository
        extends JpaRepository<Transfer, Long> {

    List<Transfer> findByFromAccountId(Long accountId);

    List<Transfer> findByToAccountId(Long accountId);

    List<Transfer> findByCreatedById(Long customerId);

}
