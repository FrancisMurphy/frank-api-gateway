package com.frank.api.gateway.config.model;

import com.frank.api.gateway.config.constant.ApiRouteDefinitionStatus;
import lombok.Data;
import lombok.NonNull;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.core.mapping.Field;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Data
public class ApiRouteDefinition {

    /**
     * 路由id
     */
    @Field("route_id")
    private String routeId;
    /**
     * 路由谓词
     */
    private List<PredicateDefinition> predicates = new ArrayList<>();
    /**
     * 过滤器
     */
    private List<FilterDefinition> filters = new ArrayList<>();
    /**
     * 跳转地址uri
     */
    private String uri;
    /**
     * 路由顺序
     */
    private Integer order;

    /**
     * 状态 0：可用 1：禁用
     * {@link com.frank.api.gateway.config.constant.ApiRouteDefinitionStatus}
     */
    private Integer status;

    public ApiRouteDefinition() {
    }

    public ApiRouteDefinition(Integer status) {
        this.status = status;
    }

    public ApiRouteDefinition(@NonNull RouteDefinition routeDefinition) {
        this.setRouteId(routeDefinition.getId());
        this.setPredicates(routeDefinition.getPredicates());
        this.setFilters(routeDefinition.getFilters());
        this.setUri(routeDefinition.getUri().toString());
        this.setOrder(routeDefinition.getOrder());
        this.setStatus(0);
    }

    public RouteDefinition getRouteDefinition() {
        RouteDefinition routeDefinition = new RouteDefinition();
        routeDefinition.setId(this.getRouteId());
        routeDefinition.setFilters(this.getFilters());
        routeDefinition.setPredicates(this.getPredicates());
        routeDefinition.setUri(URI.create(this.getUri()));
        routeDefinition.setOrder(this.getOrder());
        return routeDefinition;
    }

    /**
     * AVAILABLE
     */
    public static Example<ApiRouteDefinition> findAvailable() {
        return Example.of(new ApiRouteDefinition(ApiRouteDefinitionStatus.AVAILABLE));
    }
}
