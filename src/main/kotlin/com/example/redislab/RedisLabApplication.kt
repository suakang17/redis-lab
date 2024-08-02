package com.example.redislab

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RedisLabApplication

fun main(args: Array<String>) {
    runApplication<RedisLabApplication>(*args)
}
