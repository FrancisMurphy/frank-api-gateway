package com.frank.api.gateway.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.frank.api.gateway.auth.constant.ApiGatewayAuthResponseCode;
import com.frank.api.gateway.auth.pojo.AppInfoWithToken;
import lombok.Data;
import lombok.NonNull;
import lombok.ToString;

/**
 * @author frank
 */
@Data
@ToString
public class AccessTokenResponse extends BasicResponse{

    /**
     * 返回的at
     */
    @JsonProperty("access_token")
    private String accessToken;

    /**
     * at 过期时间 （单位：秒）
     */
    @JsonProperty("expire_in")
    private Long expireIn;

    public AccessTokenResponse(String code, String accessToken, Long expireIn) {
        super(code);
        this.accessToken = accessToken;
        this.expireIn = expireIn;
    }

    public static AccessTokenResponse success(@NonNull AppInfoWithToken appInfoWithToken) {
        return new AccessTokenResponse(ApiGatewayAuthResponseCode.SUCCESS,appInfoWithToken.getAccessToken(),appInfoWithToken.getExpireIn());
    }
}
