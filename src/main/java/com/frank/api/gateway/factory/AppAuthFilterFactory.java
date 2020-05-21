package com.frank.api.gateway.factory;

import com.frank.api.gateway.filter.AppAuthFilter;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
//import org.springframework.cloud.gateway.filter.factory.AbstractNameValueGatewayFilterFactory;
import org.springframework.stereotype.Component;

/**
 * @author frank
 */
@Component
public class AppAuthFilterFactory extends AbstractGatewayFilterFactory<AppAuthFilterFactory.AppAuthFilterConfig> {

    private AppAuthFilter appAuthFilter;

    public AppAuthFilterFactory(AppAuthFilter appAuthFilter) {
        super(AppAuthFilterConfig.class);
        this.appAuthFilter = appAuthFilter;
    }

    @Override
    public String name() {
        return "AppAuth";
    }

    @Override
    public GatewayFilter apply(AppAuthFilterConfig config) {
        return appAuthFilter;
    }

    public static class AppAuthFilterConfig{
        public AppAuthFilterConfig() {
        }
        //doNothing
    }
}
