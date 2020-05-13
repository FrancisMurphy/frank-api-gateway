package com.frank.api.gateway.auth;

import com.frank.api.gateway.auth.model.AppInfo;
import com.frank.api.gateway.auth.repository.AppInfoRepository;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class AppInfoService {

    private AppInfoRepository appInfoRepository;

    public AppInfoService(AppInfoRepository appInfoRepository) {
        this.appInfoRepository = appInfoRepository;
    }

    public Mono<AppInfo> addAppInfo(@NonNull final AppInfo appInfo) {
        return appInfoRepository.insert(appInfo);
    }

}
