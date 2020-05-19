package com.frank.api.gateway.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.frank.api.gateway.auth.exception.ApiGatewayException;
import com.frank.api.gateway.dto.BasicResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * @author frank
 */
@Order(0)
@Slf4j
@Component
public class InitFilter implements GatewayFilter {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        exchange.getResponse().setStatusCode(HttpStatus.OK);
        exchange.getResponse().getHeaders().add("Content-Type", "application/json;charset=UTF-8");

        return chain.filter(exchange).onErrorResume(throwable -> {

            BasicResponse errorReponse = null;

            if (throwable instanceof ApiGatewayException) {

                errorReponse = BasicResponse.apiError((ApiGatewayException)throwable);

            } else {
                errorReponse = BasicResponse.error();
            }

            final ServerHttpResponse response = exchange.getResponse();

            try {
                String respBody = objectMapper.writeValueAsString(errorReponse);
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.writeWith(Mono.just(response.bufferFactory().wrap(respBody.getBytes(StandardCharsets.UTF_8))));
            } catch (JsonProcessingException e) {
                log.error("ReqValidCheckFilter error:", e);
                response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
                return response.setComplete();
            }
        });
    }
}
