package com.example.attendance.service;

import com.example.attendance.dao.LeaveDao;
import com.example.attendance.entity.LeaveEntity;
import com.example.attendance.requestDto.LeaveRequestDto;
import com.example.attendance.responseDto.LeaveResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class LeaveServiceImpl implements LeaveService{
    private final LeaveDao leaveDao;
    @Override
    public LeaveResponseDto add(LeaveRequestDto leaveRequestDto){
        LeaveEntity leaveEntity = leaveRequestDto.serialize();
        LeaveEntity leaveEntityFinal=leaveDao.save(leaveEntity);
         return  LeaveResponseDto.deserialize(leaveEntityFinal);
    }
    @Override
    public List<LeaveResponseDto> getAllByEmployeeCode(String employeeCode) throws InterruptedException {
        log.info("wait starting");
//        Thread.sleep(10000);
        log.info("wait ended");
        List<LeaveEntity> leaveEntities=leaveDao.getAllByEmployeeCode(employeeCode);
       return  leaveEntities.stream().map(LeaveResponseDto::deserialize).collect(Collectors.toList());
     //  return leaveEntities.stream().mapToLong(LeaveEntity::getNoOfDays).sum();

    }
}
