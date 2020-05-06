package com.frank.api.gateway.auth.repository;

import com.frank.api.gateway.auth.model.AppInfo;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppInfoRepository extends ReactiveMongoRepository<AppInfo,String> {
}
