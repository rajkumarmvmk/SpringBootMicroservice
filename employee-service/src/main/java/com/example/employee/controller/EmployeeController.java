package com.example.employee.controller;

import com.example.employee.exception.customException.BusinessException;
import com.example.employee.requestDto.EmployeeRequestDto;
import com.example.employee.responseDto.EmployeeResponseDto;
import com.example.employee.service.EmployeeService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/employee")
public class EmployeeController {
    private final EmployeeService employeeService;
    @PostMapping(value = "/register")
    public EmployeeResponseDto register(@Valid @RequestBody EmployeeRequestDto employeeRequestDto) throws BusinessException {
        return employeeService.register(employeeRequestDto);
    }
    @PostMapping(value = "/salary")
    @CircuitBreaker(name="Leave",fallbackMethod = "fallbackMethod")
    @ResponseStatus(HttpStatus.OK)
    @TimeLimiter(name="Leave")
    @Retry(name="Leave")
    public  CompletableFuture<Double> salaryCalculation(@RequestParam String employeeCode) throws BusinessException {

        return CompletableFuture.supplyAsync(()-> {
            try {
                return employeeService.salaryCalculation(employeeCode);
            } catch (BusinessException e) {
                throw new RuntimeException(e);
            }
        });
    }
    public CompletableFuture<Double> fallbackMethod(RuntimeException runtimeException) {
        log.info("Executing Fallback logic");
        return CompletableFuture.supplyAsync(() -> 0.000000000000000000000303030303030303);
    }

}
