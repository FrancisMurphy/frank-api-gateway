package com.frank.api.gateway.auth.impl;

import com.frank.api.gateway.auth.AppInfoService;
import com.frank.api.gateway.auth.model.AppInfo;
import com.frank.api.gateway.auth.repository.AppInfoRepository;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class AppInfoServiceImpl implements AppInfoService {

    private AppInfoRepository appInfoRepository;

    public AppInfoServiceImpl(AppInfoRepository appInfoRepository) {
        this.appInfoRepository = appInfoRepository;
    }

    @Override
    public Mono<AppInfo> addAppInfo(@NonNull final AppInfo appInfo) {
        return appInfoRepository.insert(appInfo);
    }

}