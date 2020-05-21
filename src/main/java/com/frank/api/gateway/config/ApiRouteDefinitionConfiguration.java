package com.frank.api.gateway.config;

import com.frank.api.gateway.config.model.ApiRouteDefinition;
import com.frank.api.gateway.config.repository.ApiRouteDefinitionRepository;
import com.frank.api.gateway.config.constant.ApiRouteConfigConstant;
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
            : apiRouteDefinitionRepository.findAll(ApiRouteDefinition.findAvailable())
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
        return routeId.flatMap(id -> {
            if (routes.containsKey(id)) {
                routes.remove(id);
                return apiRouteDefinitionRepository.;
            }
            return Mono.defer(() -> Mono.error(new NotFoundException("RouteDefinition not found: " + routeId)));
        });
    }
}
