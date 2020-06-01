package com.frank.api.gateway.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author frank
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class DataResponse<T> extends BasicResponse {

    private T data;

    public DataResponse(String code, T data) {
        super(code);
        this.data = data;
    }

    public DataResponse(String code, String msg, T data) {
        super(code, msg);
        this.data = data;
    }
}
