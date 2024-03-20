package com.kdt.goohae.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@RequiredArgsConstructor
@Configuration
@EnableRedisRepositories
public class RedisRepositoryConfig {

    private final RedisProperties redisProperties;


    @Bean
    public RedisConnectionFactory redisConFactory() {
        return new LettuceConnectionFactory(redisProperties.getHost(),
                redisProperties.getPort());
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConFactory());
        /**
         * setKeySerializer, setValueSerializer 설정해주는 이유는
         * RedisTemplate를 사용할 때 Spring - Redis 간 데이터 직렬화, 역직렬화 시
         * 사용하는 방식이 Jdk 직렬화 방식이기 때문입니다.
         *
         * 동작에는 문제가 없지만 redis-cli을 통해 직접 데이터를 보려고 할 때
         * 알아볼 수 없는 형태로 출력되기 때문에 적용한 설정입니다.
         * */
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        return redisTemplate;
    }

}
