package com.example.employee.exception;


import com.example.employee.exception.customException.BaseErrorCodes;
import com.example.employee.exception.customException.BusinessException;
import com.example.employee.exception.customException.TechnicalException;
import com.example.employee.exception.response.Response;
import com.example.employee.exception.response.ResponseError;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.nio.file.AccessDeniedException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Slf4j
@ControllerAdvice
public class GlobalException {
    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleException(BusinessException e) {

        log.error("-----------<<<<>>>>>>>>>>>>>>>-----------------------Business Exception - [{}]", e.getErrorCode(), e);

        Response<Object> response = Response.builder().status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error(e.getErrorCode()).message(e.getMessage()).build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ExceptionHandler(TechnicalException.class)
    public ResponseEntity<Object> handleException(TechnicalException e) {

        log.error("Technical Exception - [{}]", e.getErrorCode(), e);

        Response<Object> response = Response.builder().status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error(e.getErrorCode()).message(e.getMessage()).build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleException(AccessDeniedException e) {

        Response<Object> response = Response.builder().status(HttpStatus.FORBIDDEN.value())
                .error("AccessDenied").message("your are unauthorized to access this api").build();

        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

//    @ExceptionHandler(BadCredentialsException.class)
//    public ResponseEntity<Object> handleException(BadCredentialsException e) {
//
//        Response<Object> response = Response.builder().status(HttpStatus.FORBIDDEN.value())
//                .error("BadCredential").message("your username or password wrong").build();
//
//        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
//    }
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<Object> handleException(Exception e) {
//
//        log.error("General Exception");
//
//        Response<Object> response = Response.builder().status(HttpStatus.INTERNAL_SERVER_ERROR.value())
//                .error(BaseErrorCodes.GENERAL_EXCEPTION.name())
//                .message("error").build();
//
//        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Object> handleValidationException(MissingServletRequestParameterException e) {

        log.error("Validation Exception");

        List<ResponseError> responseErrors = new ArrayList<>();
        ResponseError responseError = ResponseError.builder().field(e.getParameterName())
                .message(getMessage(BaseErrorCodes.MISSING_MANDATORY_FIELDS.name(), null)).build();

        responseErrors.add(responseError);

        Response<Object> response = Response.builder().status(HttpStatus.BAD_REQUEST.value())
                .error(BaseErrorCodes.FIELDS_VALIDATION_EXCEPTION.name())
                .message(getMessage(BaseErrorCodes.FIELDS_VALIDATION_EXCEPTION.name(), null))
                .errorDetails(responseErrors).build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException e) {

        log.error("Validation Exception", e);

        List<ResponseError> responseErrors = new ArrayList<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            ResponseError responseError = ResponseError.builder().field(((FieldError) error).getField())
                    .message(error.getDefaultMessage()).build();

            responseErrors.add(responseError);
        });

        Response<Object> response = Response.builder().status(HttpStatus.BAD_REQUEST.value())
                .error(BaseErrorCodes.FIELDS_VALIDATION_EXCEPTION.name())
                .message(getMessage(BaseErrorCodes.FIELDS_VALIDATION_EXCEPTION.name(), null))
                .errorDetails(responseErrors).build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private String getMessage(String code, String[] errorParams) {
        String message;

        try {
            message = messageSource.getMessage(code, null, Locale.ENGLISH);
            if (!ArrayUtils.isEmpty(errorParams)) {
                message = MessageFormat.format(message, errorParams);
            }
        } catch (Exception e) {
            log.warn("Error code [{}] not found. Default to GENERAL_EXCEPTION.", code);
            message = messageSource.getMessage(BaseErrorCodes.GENERAL_EXCEPTION.name(), null, Locale.ENGLISH);
        }

        return message;
    }
}
