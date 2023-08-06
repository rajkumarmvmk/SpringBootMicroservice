package com.example.attendance.repository;

import com.example.attendance.entity.LeaveEntity;
import com.example.attendance.responseDto.LeaveResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRepository extends JpaRepository<LeaveEntity,String> {

    @Query("SELECT l FROM LeaveEntity l WHERE l.employeeCode = ?1")
    List<LeaveEntity> getAllByEmployeeCode(String employeeCode);
}
