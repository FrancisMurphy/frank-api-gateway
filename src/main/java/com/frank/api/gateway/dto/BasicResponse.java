package com.frank.api.gateway.dto;

import com.frank.api.gateway.auth.constant.ApiGatewayAuthResponseCode;
import com.frank.api.gateway.auth.exception.ApiGatewayException;
import lombok.Data;
import lombok.NonNull;
import lombok.ToString;

/**
 * @author frank
 */
@Data
@ToString
public class BasicResponse {

    private String code;

    private String msg = "";

    public BasicResponse() {
    }

    public BasicResponse(String code) {
        this.code = code;
    }

    public BasicResponse(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static BasicResponse success() {
        return new BasicResponse(ApiGatewayAuthResponseCode.SUCCESS);
    }

    public static BasicResponse apiError(@NonNull ApiGatewayException apiException) {
        return new BasicResponse(apiException.getCode(), apiException.getMsg());
    }

    public static BasicResponse error() {
        return new BasicResponse(ApiGatewayAuthResponseCode.INNER_ERROR, "系统繁忙");
    }
}
