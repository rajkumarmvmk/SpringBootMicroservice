package com.example.employee.responseDto;

import com.example.employee.entity.EmployeeEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeResponseDto {

    private String employeeCode;

    private String employeeName;

    private String domain;

    private double perMonthSalary;

    private double annualIncome;

    public static EmployeeResponseDto deserialize(EmployeeEntity employeeEntity){
        EmployeeResponseDto employeeResponseDto=new EmployeeResponseDto();
        employeeResponseDto.setEmployeeCode(employeeEntity.getEmployeeCode());
        employeeResponseDto.setEmployeeName(employeeEntity.getEmployeeName());
        employeeResponseDto.setDomain(employeeEntity.getDomain());
        employeeResponseDto.setPerMonthSalary(employeeEntity.getPerMonthSalary());
        employeeResponseDto.setAnnualIncome(employeeEntity.getAnnualIncome());
        return employeeResponseDto;

    }
}
