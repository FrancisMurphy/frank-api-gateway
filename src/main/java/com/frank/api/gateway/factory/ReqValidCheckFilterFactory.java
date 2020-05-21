package com.frank.api.gateway.factory;

import com.frank.api.gateway.filter.ReqValidCheckFilter;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

/**
 * @author frank
 */
@Component
public class ReqValidCheckFilterFactory extends
    AbstractGatewayFilterFactory<ReqValidCheckFilterFactory.ReqValidCheckConfig> {

    private ReqValidCheckFilter reqValidCheckFilter;

    public ReqValidCheckFilterFactory(ReqValidCheckFilter reqValidCheckFilter) {
        super(ReqValidCheckConfig.class);
        this.reqValidCheckFilter = reqValidCheckFilter;
    }

    @Override
    public String name() {
        return "ReqValidCheck";
    }

    @Override
    public GatewayFilter apply(ReqValidCheckConfig config) {
        return reqValidCheckFilter;
    }


    public static class ReqValidCheckConfig {
        public ReqValidCheckConfig() {
        }
        //doNothing
    }
}
