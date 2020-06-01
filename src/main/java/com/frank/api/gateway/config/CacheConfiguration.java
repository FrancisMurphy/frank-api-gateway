package com.frank.api.gateway.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author frank
 */
@Configuration
public class CacheConfiguration {

    @Bean
    @Qualifier("apiRedisTemplate")
    public ReactiveRedisTemplate<String, Object> apiRedisTemplate(final ReactiveRedisConnectionFactory reactiveRedisConnectionFactory) {
        RedisSerializer<String> keySerializer = new StringRedisSerializer();
        RedisSerializer<Object> valueSerializer = new Jackson2JsonRedisSerializer(Object.class);
        RedisSerializationContext<String, Object> serializationContext = RedisSerializationContext
            .<String, Object>newSerializationContext()
            .key(keySerializer)
            .value(valueSerializer)
            .hashKey(keySerializer)
            .hashValue(valueSerializer)
            .build();
        return new ReactiveRedisTemplate<>(reactiveRedisConnectionFactory, serializationContext);
    }

}
