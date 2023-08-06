package com.example.attendance.service;

import com.example.attendance.requestDto.LeaveRequestDto;
import com.example.attendance.responseDto.LeaveResponseDto;

import java.util.List;

public interface LeaveService {
    LeaveResponseDto add(LeaveRequestDto leaveRequestDto);
    List<LeaveResponseDto>  getAllByEmployeeCode(String employeeCode) throws InterruptedException;

}
