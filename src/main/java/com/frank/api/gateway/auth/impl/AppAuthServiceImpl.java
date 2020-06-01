package com.frank.api.gateway.auth.impl;

import com.frank.api.gateway.auth.AppAuthService;
import com.frank.api.gateway.auth.constant.ApiGatewayAuthConfigConstant;
import com.frank.api.gateway.auth.constant.ApiGatewayAuthResponseCode;
import com.frank.api.gateway.auth.dto.AppAuthReq;
import com.frank.api.gateway.auth.dto.AppInfoWithAuthDto;
import com.frank.api.gateway.auth.dto.GetAccessTokenReq;
import com.frank.api.gateway.auth.exception.ApiGatewayException;
import com.frank.api.gateway.auth.model.AppAuth;
import com.frank.api.gateway.auth.pojo.AppInfoWithToken;
import com.frank.api.gateway.auth.repository.AppAuthRepository;
import com.frank.api.gateway.auth.repository.AppInfoRepository;
import com.frank.api.gateway.util.AccessTokenGenerator;
import lombok.NonNull;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * app auth service
 * @author frank
 */
@Component
public class AppAuthServiceImpl implements AppAuthService {

    private ReactiveRedisTemplate apiRedisTemplate;

    private AppInfoRepository appInfoRepository;

    private AppAuthRepository appAuthRepository;

    public AppAuthServiceImpl(ReactiveRedisTemplate apiRedisTemplate,
        AppInfoRepository appInfoRepository,
        AppAuthRepository appAuthRepository) {
        this.apiRedisTemplate = apiRedisTemplate;
        this.appInfoRepository = appInfoRepository;
        this.appAuthRepository = appAuthRepository;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Mono<AppInfoWithToken> getAccessToken(@NonNull GetAccessTokenReq req) {

        final String appId = req.getAppId();
        final String secret = req.getSecret();

        return appInfoRepository
            .findById(appId)
            .switchIfEmpty(Mono.error(new ApiGatewayException(ApiGatewayAuthResponseCode.CAN_NOT_FIND_APP_INFO,"无法找到应用信息，请检查应用信息！")))
            .filter(appInfo->appInfo.getSecret().equals(secret))
            .switchIfEmpty(Mono.error(new ApiGatewayException(ApiGatewayAuthResponseCode.APP_AUTH_INFO_ERROR,"鉴权应用信息错误，请检查应用信息！")))
            .flatMap(appInfo-> {
                final AppInfoWithToken appInfoWithToken = new AppInfoWithToken(AccessTokenGenerator.create(appInfo),appInfo);
                return
                    Mono.just(appInfoWithToken)
                        .then(
                            apiRedisTemplate
                                .opsForHash()
                                .put(ApiGatewayAuthConfigConstant.ACCESSTOKEN_CACHE_KEY, appInfoWithToken.getAccessToken(), appInfoWithToken.getAppId()))
                        .then(Mono.just(appInfoWithToken));
            });
    }

    @Override
    @SuppressWarnings("unchecked")
    public Mono<AppInfoWithAuthDto> getAppAuthInfo(@NonNull final AppAuthReq req) {

        final String accessToken = req.getAccessToken();

        return apiRedisTemplate.opsForHash()
            .get(ApiGatewayAuthConfigConstant.ACCESSTOKEN_CACHE_KEY,accessToken)
            .switchIfEmpty(Mono.error(new ApiGatewayException(ApiGatewayAuthResponseCode.INVALID_ACCESS_TOKEN,"无效的请求令牌，请检查accessToken!")))
            .flatMap(appId-> appInfoRepository
                .findById(String.valueOf(appId))
                .switchIfEmpty(Mono.error(new ApiGatewayException(ApiGatewayAuthResponseCode.CAN_NOT_FIND_APP_INFO,"无法找到应用信息，请检查应用信息！")))
                .zipWith(
                    appAuthRepository
                        .findAll(AppAuth.findByAppId(String.valueOf(appId)))
                        .collectList(), AppInfoWithAuthDto::new));
    }

}