package com.frank.api.gateway.config.repository;

import com.frank.api.gateway.config.model.ApiRouteDefinition;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * @author frank
 */
@Repository
public interface ApiRouteDefinitionRepository extends ReactiveMongoRepository<ApiRouteDefinition,String> {

    /**
     * Find all api route by status
     * @param status 路由条件
     * @return 符合条件的路由信息
     */
    Flux<ApiRouteDefinition> findAllByStatus(Integer status);

}
