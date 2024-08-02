package com.example.redislab

import io.lettuce.core.ReadFrom
import io.lettuce.core.cluster.ClusterClientOptions
import io.lettuce.core.cluster.ClusterTopologyRefreshOptions
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.*
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.StringRedisTemplate
import java.time.Duration

@Configuration
class RedisConfig {

    @Value("\${spring.data.redis.cluster.nodes}")
    lateinit var redisClusterNodes: String

    @Value("\${spring.data.redis.timeout}")
    lateinit var commandTimeout: String

    @Bean
    fun redisClusterConfiguration(): RedisClusterConfiguration {
        val nodes = redisClusterNodes.split(",").map { node ->
            val (host, port) = node.split(":")
            RedisNode(host, port.toInt())
        }
        return RedisClusterConfiguration().apply {
            setClusterNodes(nodes)
        }
    }

    @Bean
    fun redisConnectionFactory(): RedisConnectionFactory {
        // Topology refresh options
        val clusterTopologyRefreshOptions = ClusterTopologyRefreshOptions.builder()
            .enableAllAdaptiveRefreshTriggers()
            .build()

        // Client options
        val clientOptions = ClusterClientOptions.builder()
            .topologyRefreshOptions(clusterTopologyRefreshOptions)
            .build()

        // Command timeout

        // Client configuration
        val clientConfiguration = LettuceClientConfiguration.builder()
            .clientOptions(clientOptions)
            .build()

        return LettuceConnectionFactory(redisClusterConfiguration(), clientConfiguration)
    }

    @Bean
    fun stringRedisTemplate(redisConnectionFactory: RedisConnectionFactory): StringRedisTemplate {
        return StringRedisTemplate(redisConnectionFactory)
    }
}
