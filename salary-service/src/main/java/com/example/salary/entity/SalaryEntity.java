package com.example.salary.entity;

import com.example.salary.enumBox.Month;
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
@Table(name = "salary")
public class SalaryEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name ="uuid2", strategy = "uuid2" )
    private String id;
    @Column(length = 30,nullable = false)
    private String employeeName;
    private Month month;
    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    @Min(value = 0, message = "Price must be non-negative.")
    @Max(value = 1_000_000, message = "Quantity must be less than or equal to ,1,000,000.")
    private double taxAmount;

    @Transient
    private double annualIncome;

}
