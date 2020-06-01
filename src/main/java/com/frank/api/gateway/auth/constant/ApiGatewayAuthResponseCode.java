package com.frank.api.gateway.auth.constant;

/**
 * 鉴权错误码 500000-599999
 * @author frank
 */
public interface ApiGatewayAuthResponseCode {

    /**
     * 处理成功
     */
    String SUCCESS = "000000";

    /**
     * 系统繁忙
     */
    String INNER_ERROR = "100000";

    /**
     * 无法找到应用信息，请检查应用信息
     */
    String CAN_NOT_FIND_APP_INFO = "500000";

    /**
     * 鉴权应用信息错误，请检查应用信息
     */
    String APP_AUTH_INFO_ERROR = "500001";

    /**
     * 无效的at
     */
    String INVALID_ACCESS_TOKEN = "500002";

    /**
     * URL_NOT_AUTHORIZED
     */
    String URL_NOT_AUTHORIZED = "500003";

}
