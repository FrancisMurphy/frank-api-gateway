package com.frank.api.gateway.factory;

import com.frank.api.gateway.filter.InitFilter;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

/**
 * @author frank
 */
@Component
public class InitFilterFactory extends AbstractGatewayFilterFactory<InitFilterFactory.InitFilterConfig> {

    private InitFilter initFilter;

    public InitFilterFactory(InitFilter initFilter) {
        super(InitFilterConfig.class);
        this.initFilter = initFilter;
    }

    @Override
    public String name() {
        return "Init";
    }

    @Override
    public GatewayFilter apply(InitFilterConfig config) {
        return initFilter;
    }

    public static class InitFilterConfig {
        public InitFilterConfig() {
        }
        //doNothing
    }

}
