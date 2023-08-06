package com.example.attendance.dao;

import com.example.attendance.entity.LeaveEntity;
import com.example.attendance.repository.LeaveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class LeaveDaoImpl implements LeaveDao{
    private final LeaveRepository leaveRepository;
    @Override
    public LeaveEntity save(LeaveEntity leaveEntity){
        return leaveRepository.save(leaveEntity);
    }
    @Override
   public List<LeaveEntity> getAllByEmployeeCode(String employeeCode){
        return  leaveRepository.getAllByEmployeeCode(employeeCode);
   }
}
