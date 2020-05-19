package com.frank.api.gateway.auth.model;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Example;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Data
public class AppAuth {

    @Id
    private String id;

    private String appId;

    private String authService;

    /**
     * 权限url
     */
    private Set<String> authInterfaces;

    /**
     * 附属参数
     */
    private Map<String,String> attachParams = new HashMap<>();

    public AppAuth() {
    }

    public AppAuth(String appId) {
        this.appId = appId;
    }

    public static Example<AppAuth> findByAppId(@NonNull String appId) {
        return Example.of(new AppAuth(appId));
    }
}
