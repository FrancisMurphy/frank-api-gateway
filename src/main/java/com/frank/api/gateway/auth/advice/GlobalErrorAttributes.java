package com.frank.api.gateway.auth.advice;

import com.frank.api.gateway.auth.exception.ApiGatewayException;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author frank
 */
@Component
public class GlobalErrorAttributes extends DefaultErrorAttributes {

    public GlobalErrorAttributes() {
        super(false);
    }

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, boolean includeStackTrace) {
        return assembleError(request);
    }

    private Map<String, Object> assembleError(ServerRequest request) {
        Map<String, Object> errorAttributes = new LinkedHashMap<>();
        Throwable error = getError(request);
        if (error instanceof ApiGatewayException) {
            errorAttributes.put("code", ((ApiGatewayException) error).getCode());
            errorAttributes.put("data", ((ApiGatewayException)error).getMsg());
        } else {
            errorAttributes.put("code", "500");
            errorAttributes.put("data", "系统繁忙");
        }
        return errorAttributes;
    }
}
