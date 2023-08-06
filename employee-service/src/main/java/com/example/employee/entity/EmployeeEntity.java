package com.example.employee.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@ToString
@Table(name = "employee")
public class EmployeeEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name ="uuid2", strategy = "uuid2" )
    private String id;
    @Column(length =5,nullable = false,unique = true)
    private String employeeCode;
    @Column(length =30,nullable = false)
    private String employeeName;
    @Column(length =30,nullable = false)
    private String domain;
    @Column(nullable = false)
    @Min(value = 0, message = "Price must be non-negative.")
    @Max(value = 5_00_000, message = "Quantity must be less than or equal to ,5,00,000.")
    private double perMonthSalary;
    @Column(nullable = false)
    @Min(value = 0, message = "Price must be non-negative.")
    @Max(value = 3_000_000, message = "Quantity must be less than or equal to ,3,000,000.")
    private double annualIncome;

}
