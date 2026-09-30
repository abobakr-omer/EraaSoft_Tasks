package com.spring.demo.repo;

import com.spring.demo.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {

    List<Email> findByName(String name);

    List<Email> findByNameIn(List<String> names);

    List<Email> findByContent(String content);
}