package com.frank.api.gateway.constant;

/**
 * @author frank
 */

public enum ApiResponseCode {

    /**
     * 处理成功
     */
    SUCCESS("0"),

    /**
     * 无法找到路由配置信息
     */
    CAN_NOT_FIND_API_ROUTE_DEFINITION("10000"),

    /**
     * Illegal request parameter
     */
    ILLEGAL_REQUEST_PARAMETER("20000"),

    /**
     * app_id 为空
     */
    APP_ID_EMPTY("20001"),

    /**
     * access_token 为空
     */
    ACCESS_TOKEN_EMPTY("20002"),

    /**
     * 请求处理失败
     */
    PROCESS_REQ_FAIL("20003");

    ApiResponseCode(String code) {
        this.code = code;
    }

    private String code;

    public String getCode() {
        return this.code;
    }

}
