package com.frank.api.gateway.dto;

import lombok.Data;
import lombok.ToString;

/**
 * @author frank
 */
@Data
@ToString
public class BasicResponse {

    private String code;

    private String msg;

    public BasicResponse(String code) {
        this.code = code;
    }

    public BasicResponse(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}