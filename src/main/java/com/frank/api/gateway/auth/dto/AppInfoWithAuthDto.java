package com.frank.api.gateway.auth.dto;

import com.frank.api.gateway.auth.constant.ApiGatewayAuthResponseCode;
import com.frank.api.gateway.auth.exception.ApiGatewayException;
import com.frank.api.gateway.auth.model.AppAuth;
import com.frank.api.gateway.auth.model.AppInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author frank
 */
@AllArgsConstructor
public class AppInfoWithAuthDto {

    @Getter
    private AppInfo appInfo;

    private List<AppAuth> appAuthList;

    public AppAuth check(@NonNull final String targetInterface) {
        for (AppAuth appAuth : appAuthList) {
            if(appAuth.getAuthInterfaces().contains(targetInterface)) {
                return appAuth;
            }
        }
        throw new ApiGatewayException(ApiGatewayAuthResponseCode.URL_NOT_AUTHORIZED, "应用没有权限请求 接口："+targetInterface);
    }

}
