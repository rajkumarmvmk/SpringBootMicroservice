package com.example.employee.requestDto;

import com.example.employee.entity.EmployeeEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequestDto {
    @NotBlank
    private String employeeCode;
    @NotBlank
    private String employeeName;
    @NotBlank
    private String domain;
    @NotNull
    private double annualIncome;

    public EmployeeEntity serialize(){
        EmployeeEntity employeeEntity= new EmployeeEntity();
        employeeEntity.setEmployeeCode(getEmployeeCode());
        employeeEntity.setEmployeeName(getEmployeeName());
        employeeEntity.setDomain(getDomain());
        employeeEntity.setAnnualIncome(getAnnualIncome());
        employeeEntity.setPerMonthSalary(getAnnualIncome()/12);
        return employeeEntity;

    }
}
