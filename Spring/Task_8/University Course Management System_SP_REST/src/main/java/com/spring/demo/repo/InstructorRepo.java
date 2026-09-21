package com.spring.demo.repo;

import com.spring.demo.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorRepo extends JpaRepository<Instructor,Long> {

    boolean existsByEmail(String email);



}
