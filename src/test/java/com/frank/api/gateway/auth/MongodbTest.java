package com.frank.api.gateway.auth;

import com.frank.api.gateway.config.constant.ApiRouteDefinitionStatus;
import com.frank.api.gateway.config.model.ApiRouteDefinition;
import com.frank.api.gateway.config.repository.ApiRouteDefinitionRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

public class MongodbTest extends BaseApiControllerTest{

    @Autowired
    private ApiRouteDefinitionRepository apiRouteDefinitionRepository;

    @Test
    public void addAppInfoTest() {
//
//        ApiRouteDefinition apiRouteDefinition = new ApiRouteDefinition(ApiRouteDefinitionStatus.AVAILABLE);
//        ExampleMatcher.matching()
//            .withMatcher("status",ExampleMatcher.GenericPropertyMatchers.contains())

        System.out.println("xxxxxxsssss  "+apiRouteDefinitionRepository.findAllByStatus(ApiRouteDefinitionStatus.AVAILABLE).collectList().block());


    }


}
