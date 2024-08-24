package com.springBoot.bankingSystem.controller;

import com.springBoot.bankingSystem.dto.ErrorDto;
import com.springBoot.bankingSystem.exception.CustomerNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(AppExceptionHandler.class);
@ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorDto> customerNotFoundException(CustomerNotFoundException e){
    LOG.error("Customer Not Found !!!!");
//    LOG.error("CustomerNotFoundException caught: {}", e.getMessage(), e);
    return new ResponseEntity<>(ErrorDto.builder()
            .errorCode(HttpStatus.NOT_FOUND.toString())
            .message(e.getMessage())
            .build(), HttpStatus.INTERNAL_SERVER_ERROR);
}

}
