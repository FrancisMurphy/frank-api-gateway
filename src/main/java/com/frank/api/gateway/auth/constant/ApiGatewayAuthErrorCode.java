package com.frank.api.gateway.auth.constant;

/**
 * 鉴权错误码 500000-599999
 */
public interface ApiGatewayAuthErrorCode {

    /**
     * 无法找到应用信息，请检查应用信息
     */
    String CAN_NOT_FIND_APP_INFO = "500000";

    /**
     * 鉴权应用信息错误，请检查应用信息
     */
    String APP_AUTH_INFO_ERROR = "500001";

}
