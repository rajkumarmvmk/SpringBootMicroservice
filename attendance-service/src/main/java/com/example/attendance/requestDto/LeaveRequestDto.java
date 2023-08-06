package com.example.attendance.requestDto;

import com.example.attendance.entity.LeaveEntity;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LeaveRequestDto {
    @NotBlank
    private String employeeCode;

    private LocalDate startDate;

    private LocalDate endDate;


    public LeaveEntity serialize(){
        LeaveEntity leaveEntity=new LeaveEntity();
        leaveEntity.setEmployeeCode(getEmployeeCode());
        leaveEntity.setStartDate(getStartDate());
        leaveEntity.setEndDate(getEndDate());
        leaveEntity.setNoOfDays(calculateDaysBetween(getStartDate(),getEndDate()));
        return leaveEntity;
    }
    public static long calculateDaysBetween(LocalDate startDate, LocalDate endDate) {
        return ChronoUnit.DAYS.between(startDate, endDate);
    }
}
