package com.frank.api.gateway.config.repository;

import com.frank.api.gateway.config.model.ApiRouteDefinition;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiRouteDefinitionRepository extends ReactiveMongoRepository<ApiRouteDefinition,String> {
}
