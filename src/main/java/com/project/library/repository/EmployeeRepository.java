package com.project.library.repository;

import com.project.library.model.Employee;
import com.project.library.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    @Query(value = "SELECT * FROM employees where employee_id = :id",nativeQuery = true)
    Section findEmployeeById(@Param("id") int id);

}
