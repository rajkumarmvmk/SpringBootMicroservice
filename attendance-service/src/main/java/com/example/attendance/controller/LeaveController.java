package com.example.attendance.controller;

import com.example.attendance.requestDto.LeaveRequestDto;
import com.example.attendance.responseDto.LeaveResponseDto;
import com.example.attendance.service.LeaveService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/leave")
public class LeaveController {
    private final LeaveService leaveService;
    @PostMapping("/add")
    public LeaveResponseDto add(@Valid @RequestBody LeaveRequestDto leaveRequestDto){
        return leaveService.add(leaveRequestDto);
    }
    @GetMapping("/getAllByEmployeeCode")
    public  List<LeaveResponseDto>  getAllByEmployeeCode(@RequestParam String employeeCode) throws InterruptedException {
        return leaveService.getAllByEmployeeCode(employeeCode);
    }
}
