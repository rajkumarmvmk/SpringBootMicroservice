package com.example.employee.dao;

import com.example.employee.entity.EmployeeEntity;
import com.example.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class EmployeeDaoImpl implements EmployeeDao{
    private final EmployeeRepository employeeRepository;
    @Override
    public Optional<EmployeeEntity> findByEmployeeCode(String employeeCode){
        return employeeRepository.findByEmployeeCode(employeeCode);
    }
    @Override
    public EmployeeEntity save(EmployeeEntity employeeEntity){
        return employeeRepository.save(employeeEntity);
    }
}
