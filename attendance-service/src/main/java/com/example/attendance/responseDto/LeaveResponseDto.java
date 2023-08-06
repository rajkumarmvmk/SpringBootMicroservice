package com.example.attendance.responseDto;

import com.example.attendance.entity.LeaveEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
@Getter
@Setter
@Slf4j
public class LeaveResponseDto {

    private String employeeCode;

    private LocalDate startDate;

    private LocalDate endDate;

    private Long noOfDays;
    public static LeaveResponseDto deserialize(LeaveEntity leaveEntity){
        LeaveResponseDto leaveResponseDto=new LeaveResponseDto();
        leaveResponseDto.setEmployeeCode(leaveEntity.getEmployeeCode());
        leaveResponseDto.setStartDate(leaveEntity.getStartDate());
        leaveResponseDto.setEndDate(leaveEntity.getEndDate());
        leaveResponseDto.setNoOfDays(leaveEntity.getNoOfDays());
        return leaveResponseDto;
    }
}
