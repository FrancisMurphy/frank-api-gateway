package com.frank.api.gateway.auth.repository;

import com.frank.api.gateway.auth.model.AppAuth;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppAuthRepository extends ReactiveMongoRepository<AppAuth,String> {
}
