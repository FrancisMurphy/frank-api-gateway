package com.frank.api.gateway.filter;

import com.frank.api.gateway.auth.AppAuthService;
import com.frank.api.gateway.auth.constant.ApiGatewayAuthResponseCode;
import com.frank.api.gateway.auth.dto.AppAuthReq;
import com.frank.api.gateway.auth.exception.ApiGatewayException;
import com.frank.api.gateway.constant.ApiParamKeys;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 应用鉴权
 *
 * @author wangpeng
 * @date 2018/06/06
 */
@Component
public class AppAuthFilter implements GatewayFilter, Ordered
{

    private AppAuthService appAuthService;

    public AppAuthFilter(AppAuthService appAuthService) {
        this.appAuthService = appAuthService;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        final ServerHttpRequest request = exchange.getRequest();

        final String targetInterface = request.getPath().value();
        final String accessToken = request.getHeaders().getFirst(ApiParamKeys.REQ_HEAD_ACCESS_TOKEN);

        return appAuthService
            .getAppAuthInfo(new AppAuthReq(accessToken))
            .flatMap(appInfoWithAuthDto ->
                Mono.just(appInfoWithAuthDto.check(targetInterface)))
            //将参数上下文带入到请求中
            .flatMap(appAuth -> chain.filter(exchange.mutate().request(request.mutate()
                .headers((headers) -> appAuth.getAttachParams().forEach((k, v) -> headers.add("aaap_" + k, v))).build())
                .build()));
    }

    @Override
    public int getOrder() {
        return 2;
    }
}
