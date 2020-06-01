package com.frank.api.gateway.auth.pojo;

import com.frank.api.gateway.auth.model.AppInfo;
import lombok.Data;
import lombok.ToString;

/**
 * @author frank
 */
@Data
@ToString
public class AppInfoWithToken extends AppInfo {

    /**
     * 默认AccessToken过期时间
     */
    public final static Long DEFALUT_TOKEN_EXPIRE = 7200l;

    private String accessToken;

    private Long expireIn = DEFALUT_TOKEN_EXPIRE;

    public AppInfoWithToken() {
    }

    public AppInfoWithToken(String accessToken, AppInfo appInfo) {
        super(appInfo);
        this.accessToken = accessToken;
    }

    public AppInfoWithToken(String accessToken, Long expireIn, AppInfo appInfo) {
        super(appInfo);
        this.accessToken = accessToken;
        this.expireIn = expireIn;
    }

}
