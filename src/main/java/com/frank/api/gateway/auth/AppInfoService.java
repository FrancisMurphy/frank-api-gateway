package com.frank.api.gateway.auth;

import com.frank.api.gateway.auth.model.AppInfo;
import lombok.NonNull;
import reactor.core.publisher.Mono;

public interface AppInfoService {

    Mono<AppInfo> addAppInfo(@NonNull final AppInfo appInfo);

}
