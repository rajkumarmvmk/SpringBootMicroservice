package com.example.employee.responseDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

@Getter
@Setter
@Slf4j
@ToString
public class LeaveResponseDto {

    private String employeeCode;

    private LocalDate startDate;

    private LocalDate endDate;

    private Long noOfDays;
}
