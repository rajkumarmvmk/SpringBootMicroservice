package com.example.attendance.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Builder
@ToString
@Table(name = "leaveRecord")
public class LeaveEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name ="uuid2", strategy = "uuid2" )
    private String id;

    @Column(length = 6,nullable = false)
    private String employeeCode;

    private LocalDate startDate;

    private LocalDate endDate;

    @Column(length = 6,nullable = false)
    private Long noOfDays;

}
