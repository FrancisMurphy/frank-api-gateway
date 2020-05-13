package com.frank.api.gateway.util;

import com.frank.api.gateway.constant.ApiParamKeys;
import com.frank.api.gateway.constant.ApiResponseCode;
import com.frank.api.gateway.dto.BasicResponse;
import lombok.NonNull;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;

/**
 * Filter assistant for gateway filter
 * @author frank
 */
public class FilterAssistant {

    public static BasicResponse checkHead(@NonNull ServerWebExchange exchange,@NonNull String... headNames) {

        for(String headName : headNames) {

            //检查头
            String headValue = exchange.getRequest().getQueryParams().getFirst(headName);

            if (StringUtils.isEmpty(headValue)) {
                if(ApiParamKeys.REQ_HEAD_APP_ID.equals(headName)) {
                    return new BasicResponse(ApiResponseCode.APP_ID_EMPTY.getCode(),ApiResponseCode.APP_ID_EMPTY.name());
                }
                else {
                    return new BasicResponse(ApiResponseCode.ILLEGAL_REQUEST_PARAMETER.getCode(),ApiResponseCode.ILLEGAL_REQUEST_PARAMETER.name());
                }
            }
        }
        return null;
    }

}
