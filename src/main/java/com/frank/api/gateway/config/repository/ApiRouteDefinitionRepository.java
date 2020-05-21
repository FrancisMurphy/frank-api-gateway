package com.frank.api.gateway.config.repository;

import com.frank.api.gateway.config.model.ApiRouteDefinition;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ApiRouteDefinitionRepository extends ReactiveMongoRepository<ApiRouteDefinition,String> {

    /**
     * @param status 路由条件
     * @return
     */
    Flux<ApiRouteDefinition> findAllByStatus(Integer status);

}
