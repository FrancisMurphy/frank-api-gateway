package com.frank.api.gateway.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author frank
 */
@Data
@AllArgsConstructor
public class GetAccessTokenReq {

    private String appId;

    private String secret;
}
