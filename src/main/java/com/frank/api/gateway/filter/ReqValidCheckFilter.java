package com.frank.api.gateway.filter;

import com.frank.api.gateway.constant.ApiParamKeys;
import com.frank.api.gateway.util.FilterAssistant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 检查参数格式是否合法
 *
 * @author frank
 */
@Slf4j
@Component("reqValidCheckFilter")
public class ReqValidCheckFilter implements GatewayFilter, Ordered{

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        final ServerHttpRequest request = exchange.getRequest();
        FilterAssistant.checkHead(request,ApiParamKeys.REQ_HEAD_ACCESS_TOKEN);
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 1;
    }

}
