package com.frank.api.gateway.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author frank
 */
@Data
@AllArgsConstructor
public class AppAuthReq {

    private String accessToken;

}
