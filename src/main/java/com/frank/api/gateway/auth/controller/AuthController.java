package com.frank.api.gateway.auth.controller;

import com.frank.api.gateway.dto.AccessTokenResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController("auth")
public class AuthController {

    @GetMapping("token")
    public Mono<AccessTokenResponse> getAccessToken(@RequestParam("appid") String appId, @RequestParam("secret") String secret) {





    }

}
