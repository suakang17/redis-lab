package com.example.redislab

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisClusterConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.StringRedisSerializer

@Configuration
class RedisConfig {
    @Bean
    fun redisConnectionFactory(): LettuceConnectionFactory {
        val clusterConfiguration = RedisClusterConfiguration(
            mutableListOf(
                "127.0.0.1:6379", "127.0.0.1:6479", "127.0.0.1:6579",
                "127.0.0.1:6380", "127.0.0.1:6480", "127.0.0.1:6580"
            )
        )
        return LettuceConnectionFactory(clusterConfiguration)
    }

    @Bean
    fun redisTemplate(): RedisTemplate<String, Any> {
        val template = RedisTemplate<String, Any>()
        template.setConnectionFactory(redisConnectionFactory())
        template.keySerializer = StringRedisSerializer()
        template.valueSerializer = StringRedisSerializer()
        return template
    }
}

