package com.frank.api.gateway.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.frank.api.gateway.constant.ApiParamKeys;
import com.frank.api.gateway.dto.BasicResponse;
import com.frank.api.gateway.util.FilterAssistant;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 检查参数格式是否合法
 *
 * @author frank
 */
@Order(1)
@Slf4j
@Component("reqValidCheckFilter")
public class ReqValidCheckFilter implements GatewayFilter{

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        final ServerHttpRequest request = exchange.getRequest();

        final BasicResponse errorResponse = FilterAssistant.checkHead(request,ApiParamKeys.REQ_HEAD_ACCESS_TOKEN);

        if(errorResponse == null) {
            return chain.filter(exchange);
        }

        final ServerHttpResponse response = exchange.getResponse();

        try {
            String respBody = objectMapper.writeValueAsString(errorResponse);
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.writeWith(Mono.just(response.bufferFactory().wrap(respBody.getBytes(StandardCharsets.UTF_8))));
        } catch (JsonProcessingException e) {
            log.error("ReqValidCheckFilter error:",e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            return response.setComplete();
        }
    }
}
