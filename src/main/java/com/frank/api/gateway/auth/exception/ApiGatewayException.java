package com.frank.api.gateway.auth.exception;

import lombok.Data;
import lombok.ToString;

/**
 * @author frank
 */
@Data
@ToString
public class ApiGatewayException extends RuntimeException{

    private String code;

    private String msg;

    public ApiGatewayException(String code,String msg) {
        this.code = code;
        this.msg = msg;
    }
}
