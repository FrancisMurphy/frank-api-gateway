package com.frank.api.gateway.auth.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
public class AppAuth {

    @Id
    private String id;

    private String appId;

    private String authService;

    private List<String> authInterfaces;

}
