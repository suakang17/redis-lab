package com.example.redislab

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service


@Service
class RedisService {
    @Autowired
    private val redisTemplate: RedisTemplate<String, Any>? = null

    fun setValue(key: String, value: String) {
        redisTemplate?.opsForValue()?.set(key, value)
    }

    fun getValue(key: String): String? {
        return redisTemplate?.opsForValue()?.get(key) as String?
    }

}
