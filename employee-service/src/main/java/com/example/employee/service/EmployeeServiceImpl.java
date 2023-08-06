package com.example.employee.service;

import com.example.employee.dao.EmployeeDao;
import com.example.employee.entity.EmployeeEntity;
import com.example.employee.event.EmployeeCode;
import com.example.employee.exception.customException.BaseErrorCodes;
import com.example.employee.exception.customException.BusinessException;
import com.example.employee.responseDto.LeaveResponseDto;
import com.example.employee.requestDto.EmployeeRequestDto;
import com.example.employee.responseDto.EmployeeResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Component
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDao employeeDao;
    private final WebClient.Builder webClientBuilder;
    private final KafkaTemplate<String, EmployeeCode> kafkaTemplate;
    private final ApplicationEventPublisher applicationEventPublisher;
    @Override
    public EmployeeResponseDto register(EmployeeRequestDto employeeRequestDto) throws BusinessException {
        Optional<EmployeeEntity> employeeEntity=employeeDao.findByEmployeeCode(employeeRequestDto.getEmployeeCode());
        if(employeeEntity.isEmpty()){
            EmployeeEntity employeeEntityFinal=employeeRequestDto.serialize();
            employeeDao.save(employeeEntityFinal);
            kafkaTemplate.send("notificationTopic",new EmployeeCode(employeeEntityFinal.getEmployeeCode()));
         return  EmployeeResponseDto.deserialize(employeeEntityFinal);
        }else{
            throw new BusinessException(BaseErrorCodes.DUPLICATE_RECORD.name(),BaseErrorCodes.DUPLICATE_RECORD.message());
        }
    }
    @Override
    public Double salaryCalculation(String employeeCode) throws BusinessException {
        log.info("----------------------------------");
        Optional<EmployeeEntity> employeeEntity=employeeDao.findByEmployeeCode(employeeCode);
        log.info("----------------------------------");
        if(employeeEntity.isPresent()){
            List<LeaveResponseDto> list=webClientBuilder.build().get()
                        .uri("http://attendance-service/api/v1/leave/getAllByEmployeeCode",
                                uriBuilder -> uriBuilder.queryParam("employeeCode", employeeCode).build())
                        .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<List<LeaveResponseDto>>() {})
                       .block();

            assert list != null;
            log.info("===================={}++++++++++++",list.getClass());
            log.info("<<<<<<<<<<<<<<<<<<<<<<<{}>>>>>>>>>>>>>>>>>>>>>",list);
            long noOfDays=list.stream().mapToLong(LeaveResponseDto::getNoOfDays).sum();
            if(!(noOfDays==0)) {
                double perDaySalary=(employeeEntity.get().getPerMonthSalary())/26;
                double salary = perDaySalary * noOfDays;
                return (employeeEntity.get().getPerMonthSalary())-salary;
            }else {
                return employeeEntity.get().getPerMonthSalary();
            }
       }
        else{
            throw new BusinessException(BaseErrorCodes.RECORD_NOT_FOUND.name(),BaseErrorCodes.RECORD_NOT_FOUND.message());
        }

    }
}
