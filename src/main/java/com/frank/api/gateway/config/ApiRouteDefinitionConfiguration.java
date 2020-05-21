package com.frank.api.gateway.config;

import com.frank.api.gateway.auth.exception.ApiGatewayException;
import com.frank.api.gateway.config.constant.ApiRouteDefinitionStatus;
import com.frank.api.gateway.config.model.ApiRouteDefinition;
import com.frank.api.gateway.config.repository.ApiRouteDefinitionRepository;
import com.frank.api.gateway.config.constant.ApiRouteConfigConstant;
import com.frank.api.gateway.constant.ApiResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author frank
 */
@Slf4j
@Component
public class ApiRouteDefinitionConfiguration implements RouteDefinitionRepository {

    /**
     * local route cachea
     */
    private Map<String, RouteDefinition> routes = new ConcurrentHashMap<>();

    private ApiRouteDefinitionRepository apiRouteDefinitionRepository;

    public ApiRouteDefinitionConfiguration(ApiRouteDefinitionRepository apiRouteDefinitionRepository) {
        this.apiRouteDefinitionRepository = apiRouteDefinitionRepository;
    }

    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        return routes.size() > 0 ? Flux.fromIterable(routes.values())
            : apiRouteDefinitionRepository.findAllByStatus(ApiRouteDefinitionStatus.AVAILABLE)
                .map(apiRouteDefinition ->{
                    RouteDefinition routeDefinition = apiRouteDefinition.getRouteDefinition();
                    routes.put(routeDefinition.getId(), routeDefinition);
                    return routeDefinition;
                });
    }

    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        return route.flatMap(routeDefinition ->
            apiRouteDefinitionRepository.save(
                new ApiRouteDefinition(routeDefinition))
                .zipWith(
                    Mono.fromRunnable(
                        ()-> routes.put(routeDefinition.getId(), routeDefinition)))
                .then()
        );
    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return routeId.flatMap(id-> apiRouteDefinitionRepository.findById(id))
            .switchIfEmpty(Mono.error(new ApiGatewayException(ApiResponseCode.CAN_NOT_FIND_API_ROUTE_DEFINITION.getCode(),
                ApiResponseCode.CAN_NOT_FIND_API_ROUTE_DEFINITION.name())))
            .flatMap(apiRouteDefinition ->
                apiRouteDefinitionRepository.save(apiRouteDefinition.unavail())
                    .then(Mono.fromRunnable(
                        ()->routes.remove(apiRouteDefinition.getRouteId())))
        );
    }
}
