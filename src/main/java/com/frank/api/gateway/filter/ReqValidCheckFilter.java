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
@Slf4j
@Component("reqValidCheckFilter")
public class ReqValidCheckFilter implements GatewayFilter ,Ordered{

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
            DataBuffer buffer = response.bufferFactory().wrap(respBody.getBytes(StandardCharsets.UTF_8));
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.writeWith(Mono.just(buffer));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            return response.setComplete();
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }

    //    private ServerHttpResponseDecorator processResponse(ServerHttpResponse response, DataBufferFactory bufferFactory, Object obj) {
//        return new ServerHttpResponseDecorator(response) {
//
//            @SuppressWarnings("unchecked")
//            @Override
//            public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
//                if (body instanceof Flux) {
//                    Flux<? extends DataBuffer> flux = (Flux<? extends DataBuffer>) body;
//                    return super.writeWith(flux.map(buffer -> {
//                        CharBuffer charBuffer = StandardCharsets.UTF_8.decode(buffer.asByteBuffer());
//                        DataBufferUtils.release(buffer);
//                        JsonNode jsonNode = readNode(charBuffer.toString());
//                        JsonNode payload = jsonNode.get("payload");
//                        String text = payload.toString();
//                        String content = AesUtils.X.encrypt(text);
//                        log.info("修改响应体payload,修改前:{},修改后:{}", text, content);
//                        setPayloadTextNode(content, jsonNode);
//                        return bufferFactory.wrap(jsonNode.toString().getBytes(StandardCharsets.UTF_8));
//                    }));
//                }
//                return super.writeWith(body);
//            }
//        };
//    }

//    public Mono<Void> processResp(ServerWebExchange exchange, GatewayFilterChain chain){
//        ServerHttpResponse response = exchange.getResponse();
//
//
//    }
}
