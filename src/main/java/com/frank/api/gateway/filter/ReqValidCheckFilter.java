package com.frank.api.gateway.filter;

import com.frank.api.gateway.constant.ApiParamKeys;
import com.frank.api.gateway.dto.BasicResponse;
import com.frank.api.gateway.util.FilterAssistant;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 检查参数格式是否合法
 *
 * @author frank
 */
@Component
public class ReqValidCheckFilter implements GatewayFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //检查头
        String appId = exchange.getRequest().getQueryParams().getFirst(ApiParamKeys.REQ_HEAD_APP_ID);

        BasicResponse errorResponse = FilterAssistant.checkHead(exchange,ApiParamKeys.REQ_HEAD_APP_ID);
        if(null == errorResponse) {

        }


        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
