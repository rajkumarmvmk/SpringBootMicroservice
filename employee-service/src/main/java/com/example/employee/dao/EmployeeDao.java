package com.example.employee.dao;

import com.example.employee.entity.EmployeeEntity;

import java.util.Optional;

public interface EmployeeDao {
    Optional<EmployeeEntity> findByEmployeeCode(String employeeCode);
    EmployeeEntity save(EmployeeEntity employeeEntity);
}
