package com.spring.demo.repo;
import com.spring.demo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Long> {



    @Query(
            value = "SELECT * FROM Employee " +
                    "WHERE UPPER(name) LIKE UPPER(CONCAT(CONCAT('%', :name), '%'))",
            nativeQuery = true
    )
    List<Employee> searchByNameNative(@Param("name") String name);


    @Query(
            "SELECT e FROM Employee e " +
                    "WHERE UPPER(e.name) LIKE UPPER(CONCAT(CONCAT('%', :name), '%'))"
    )
    List<Employee> searchByNameNonNative(@Param("name") String name);

    List<Employee> findByNameContainingIgnoreCase(String name);

}
