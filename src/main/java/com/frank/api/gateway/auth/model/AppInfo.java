package com.frank.api.gateway.auth.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
@ToString
@AllArgsConstructor
public class AppInfo {

    @Id
    private String appId;

    private String secret;

    private Integer type;

    private Date createTime;

    private Date updateTime;

    public AppInfo() {
    }

    public AppInfo(AppInfo appInfo) {
        this.appId = appInfo.getAppId();
        this.secret = appInfo.getSecret();
        this.type = appInfo.getType();
        this.createTime = appInfo.getCreateTime();
        this.updateTime = appInfo.getUpdateTime();
    }
}
