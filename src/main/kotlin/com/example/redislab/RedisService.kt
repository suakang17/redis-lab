package com.example.redislab

import org.slf4j.LoggerFactory
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Service

@Service
class RedisService(
    private val redisTemplate: StringRedisTemplate
) {
    private val logger = LoggerFactory.getLogger(RedisService::class.java)

    fun writeData(key: String, value: String) {
        try {
            redisTemplate.opsForValue().set(key, value)
            logger.info("Successfully wrote data for key: $key")
        } catch (e: Exception) {
            logger.error("Failed to write data for key: $key", e)
            throw RuntimeException("Failed to write data to Redis", e)
        }
    }

    fun readData(key: String): String? {
        return try {
            redisTemplate.opsForValue().get(key)?.also {
                logger.info("Successfully read data for key: $key")
            }
        } catch (e: Exception) {
            logger.error("Failed to read data for key: $key", e)
            throw RuntimeException("Failed to read data from Redis", e)
        }
    }
}