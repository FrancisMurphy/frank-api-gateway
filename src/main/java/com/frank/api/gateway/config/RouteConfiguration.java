package com.frank.api.gateway.config;

import com.frank.api.gateway.filter.InitFilter;
import com.frank.api.gateway.filter.ReqValidCheckFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfiguration {

    public RouteLocator customerRouteLocator(RouteLocatorBuilder builder) {
        // @formatter:off
    return builder.routes()
            .route(r -> r.path("/fluent/customer/**")
                         .filters(f -> f.stripPrefix(2)
                                        .filters(new InitFilter(),new ReqValidCheckFilter())
                                        .addResponseHeader("X-Response-Default-Foo", "Default-Bar"))
                         .uri("lb://CONSUMER")
                         .order(0)
                         .id("fluent_customer_service")
            )
            .build();
    // @formatter:on
    }

}
