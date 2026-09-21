package com.spring.demo.repo;

import com.spring.demo.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepo extends JpaRepository<Course,Long> {

    boolean existsByCourseCode(String courseCode);


}
