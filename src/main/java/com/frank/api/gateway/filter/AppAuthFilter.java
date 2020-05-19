package com.frank.api.gateway.filter;

import com.frank.api.gateway.auth.AppAuthService;
import com.frank.api.gateway.auth.constant.ApiGatewayAuthResponseCode;
import com.frank.api.gateway.auth.dto.AppAuthReq;
import com.frank.api.gateway.auth.exception.ApiGatewayException;
import com.frank.api.gateway.constant.ApiParamKeys;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
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
@Order(2)
@Component
public class AppAuthFilter implements GatewayFilter
{

    private AppAuthService appAuthService;

    public AppAuthFilter(AppAuthService appAuthService) {
        this.appAuthService = appAuthService;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        final ServerHttpRequest request = exchange.getRequest();

        final String targetInterface = request.getPath().contextPath().value();
        final String accessToken = request.getHeaders().getFirst(ApiParamKeys.REQ_HEAD_ACCESS_TOKEN);

        return appAuthService
            .getAppAuthInfo(new AppAuthReq(accessToken))
            .flatMap(appInfoWithAuthDto ->
                Mono.just(appInfoWithAuthDto.check(targetInterface))
                    .switchIfEmpty(
                        Mono.error(new ApiGatewayException(ApiGatewayAuthResponseCode.URL_NOT_AUTHORIZED, "应用没有权限请求 接口："+targetInterface))))
            //将参数上下文带入到请求中
            .flatMap(appAuth -> {
                appAuth.getAttachParams().forEach((key, value) -> request.getHeaders().add("aaap_" + key, value));
                return chain.filter(exchange);
            });
    }

}
