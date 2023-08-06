package com.example.employee.service;

import com.example.employee.exception.customException.BusinessException;
import com.example.employee.requestDto.EmployeeRequestDto;
import com.example.employee.responseDto.EmployeeResponseDto;

public interface EmployeeService {
    EmployeeResponseDto register(EmployeeRequestDto employeeRequestDto) throws BusinessException;
    Double salaryCalculation(String employeeCode) throws BusinessException;

}
