package com.frank.api.gateway.auth.controller;

import com.frank.api.gateway.auth.AccessTokenService;
import com.frank.api.gateway.auth.dto.GetAccessTokenReq;
import com.frank.api.gateway.dto.AccessTokenResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author frank
 */
@RestController("auth")
public class AuthController {

    private AccessTokenService accessTokenService;

    public AuthController(AccessTokenService accessTokenService) {
        this.accessTokenService = accessTokenService;
    }

    @GetMapping("token")
    public Mono<AccessTokenResponse> getAccessToken(@RequestParam("appid") String appId,
        @RequestParam("secret") String secret) {

        return accessTokenService.getAccessToken(new GetAccessTokenReq(appId,secret))
            .map(AccessTokenResponse::success);
    }

}
