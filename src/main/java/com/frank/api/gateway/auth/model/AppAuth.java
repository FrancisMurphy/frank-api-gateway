package com.frank.api.gateway.auth.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class AppAuth {

    @Id
    private String id;

    private String appId;

    private String authService;

    /**
     * 权限url
     */
    private List<String> authInterfaces;

    /**
     * 附属参数
     */
    private Map<String,Object> attachParams = new HashMap<>();

}
