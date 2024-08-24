package com.springBoot.bankingSystem.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class ErrorDto {

    private String message;
    private String errorCode;
}
