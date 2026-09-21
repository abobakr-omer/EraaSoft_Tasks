package com.spring.demo.repo;

import com.spring.demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<Student,Long> {


    boolean existsByEmail(String email);


}
