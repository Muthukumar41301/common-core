package com.core.lib.exceptions;

import com.core.lib.dto.CommonResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Log4j2
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidArgumentException.class)
    public ResponseEntity<CommonResponse> handleCustomException(InvalidArgumentException e) {
        log.error("InvalidArgumentException caught: {}", e.getMessage());
        CommonResponse errorResponse = CommonResponse.builder().code(400).errorMessage(e.getMessage()).build();
        return new ResponseEntity<>(errorResponse, HttpStatus.OK);
    }
}
