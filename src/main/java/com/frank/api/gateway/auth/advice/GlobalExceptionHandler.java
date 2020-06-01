package com.frank.api.gateway.auth.advice;

import com.frank.api.gateway.auth.exception.ApiGatewayException;
import com.frank.api.gateway.dto.BasicResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Controller 统一异常拦截器
 * @author frank
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(code = HttpStatus.OK)
    public BasicResponse handleException(Exception e) {
        log.error("GlobalExceptionHandler error:",e);
        return e instanceof ApiGatewayException? BasicResponse.apiError((ApiGatewayException)e) : BasicResponse.error();
    }
}
