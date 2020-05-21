package com.frank.api.gateway.config;

import com.frank.api.gateway.filter.AppAuthFilter;
import com.frank.api.gateway.filter.ExceptionHandlerFilter;
import com.frank.api.gateway.filter.InitFilter;
import com.frank.api.gateway.filter.ReqValidCheckFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author frank
 */
//@Configuration
public class RouteConfiguration {

//    @Bean
    public RouteLocator apiServiceRouteLocator(RouteLocatorBuilder builder,
        InitFilter initFilter,
        ReqValidCheckFilter reqValidCheckFilter,
        AppAuthFilter appAuthFilter) {

    return builder.routes()
            .route(r -> r.path("/apiservice/**")
                         .filters(f -> f.stripPrefix(1)
                                        .filters(
                                            initFilter,
                                            reqValidCheckFilter,
                                            appAuthFilter))
                         .uri("http://localhost:20000")
                         .order(0)
                         .id("api"))
            .build();
    }
}
