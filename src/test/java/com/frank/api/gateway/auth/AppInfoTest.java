package com.frank.api.gateway.auth;

import com.frank.api.gateway.auth.model.AppAuth;
import com.frank.api.gateway.auth.model.AppInfo;
import com.frank.api.gateway.auth.repository.AppAuthRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.*;

public class AppInfoTest extends BaseApiControllerTest{

    @Autowired
    private AppAuthRepository appAuthRepository;

    @Test
    public void addAppInfoTest() {

        AppInfo appInfo = new AppInfo();
        appInfo.setAppId("1213123");
        appInfo.setSecret("123123");
        appInfo.setType(1);
        appInfo.setCreateTime(new Date());

//        rest.post().uri("/appinfo/add").body().exchange().expectStatus().isOk();
    }

    @Test
    public void addAppAuthTest() {

        AppAuth appAuth = new AppAuth();
        appAuth.setAppId("123123123");
        String[] authInterfaces = {"/wechat/accesstoken/get"};

        Set<String> authInterface = new HashSet<>();
        authInterface.add("/wechat/accesstoken/get");

        Map<String,String> attachParamMap = new HashMap<>();
        attachParamMap.put("k1","v1");
        attachParamMap.put("k2","v2");

        appAuth.setAuthInterfaces(authInterface);
        appAuth.setAttachParams(attachParamMap);
        appAuth.setAuthService("apiservice");

        appAuthRepository.insert(appAuth).block();

//        rest.post().uri("/appinfo/add").body().exchange().expectStatus().isOk();
    }

}
