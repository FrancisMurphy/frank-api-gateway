package com.frank.api.gateway.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    public AccessTokenResponse(@NonNull String code, @NonNull String accessToken) {
        super(code);
        this.accessToken = accessToken;
    }
}
