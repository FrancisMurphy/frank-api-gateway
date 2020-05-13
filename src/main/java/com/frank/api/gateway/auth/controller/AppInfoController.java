package com.frank.api.gateway.auth.controller;

import com.frank.api.gateway.auth.AppInfoService;
import com.frank.api.gateway.auth.model.AppInfo;
import com.frank.api.gateway.dto.BasicResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
public class AppInfoController {

    private AppInfoService appInfoService;

    public AppInfoController(AppInfoService appInfoService) {
        this.appInfoService = appInfoService;
    }

    @PostMapping("appinfo/add")
    public Mono<BasicResponse> getAccessToken(@RequestBody AppInfo appInfo) {
        log.info("1111:{}",appInfo);
        return appInfoService.addAppInfo(appInfo).flatMap(vObj->Mono.just(BasicResponse.success()));
    }
}
