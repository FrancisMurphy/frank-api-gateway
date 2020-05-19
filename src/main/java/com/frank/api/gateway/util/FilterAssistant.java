package com.frank.api.gateway.util;

import com.frank.api.gateway.auth.exception.ApiGatewayException;
import com.frank.api.gateway.constant.ApiParamKeys;
import com.frank.api.gateway.constant.ApiResponseCode;
import com.frank.api.gateway.dto.BasicResponse;
import lombok.NonNull;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;

/**
 * Filter assistant for gateway filter
 * @author frank
 */
public class FilterAssistant {

    public static void checkHead(@NonNull final ServerHttpRequest request,@NonNull String... headNames) {

        for(String headName : headNames) {

            //检查头
            String headValue = request.getHeaders().getFirst(headName);

            if (StringUtils.isEmpty(headValue)) {
                if(ApiParamKeys.REQ_HEAD_ACCESS_TOKEN.equals(headName)) {
                    throw new ApiGatewayException(ApiResponseCode.ACCESS_TOKEN_EMPTY.getCode(),ApiResponseCode.ACCESS_TOKEN_EMPTY.name());
                }
                else {
                    throw new ApiGatewayException(ApiResponseCode.ILLEGAL_REQUEST_PARAMETER.getCode(),ApiResponseCode.ILLEGAL_REQUEST_PARAMETER.name());
                }
            }
        }
    }

}
