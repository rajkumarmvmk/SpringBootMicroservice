package com.example.employee.repository;

import com.example.employee.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity,String> {
    Optional<EmployeeEntity> findByEmployeeCode(String employeeCode);
}
