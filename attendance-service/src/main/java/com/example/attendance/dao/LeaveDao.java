package com.example.attendance.dao;

import com.example.attendance.entity.LeaveEntity;

import java.util.List;

public interface LeaveDao {
    LeaveEntity save(LeaveEntity leaveEntity);
    List<LeaveEntity> getAllByEmployeeCode(String employeeCode);
}
