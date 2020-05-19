package com.frank.api.gateway.auth.controller;

import com.frank.api.gateway.auth.AppAuthService;
import com.frank.api.gateway.auth.dto.GetAccessTokenReq;
import com.frank.api.gateway.dto.AccessTokenResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author frank
 */
@RestController
public class AuthController {

    private AppAuthService accessTokenService;

    public AuthController(AppAuthService accessTokenService) {
        this.accessTokenService = accessTokenService;
    }

    @GetMapping("auth/token")
    public Mono<AccessTokenResponse> getAccessToken(@RequestParam("appid") String appId,
        @RequestParam("secret") String secret) {
        return accessTokenService.getAccessToken(new GetAccessTokenReq(appId,secret))
            .map(AccessTokenResponse::success);
    }

}
