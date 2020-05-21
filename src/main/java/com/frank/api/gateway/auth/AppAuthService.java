package com.frank.api.gateway.auth;

import com.frank.api.gateway.auth.dto.AppAuthReq;
import com.frank.api.gateway.auth.dto.AppInfoWithAuthDto;
import com.frank.api.gateway.auth.dto.GetAccessTokenReq;
import com.frank.api.gateway.auth.pojo.AppInfoWithToken;
import lombok.NonNull;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @author frank
 */
@Component
public interface AppAuthService {

    Mono<AppInfoWithToken> getAccessToken(@NonNull GetAccessTokenReq req);

    Mono<AppInfoWithAuthDto> getAppAuthInfo(@NonNull final AppAuthReq req);

}
