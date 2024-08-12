package com.example.redislab

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class RedisController(private val service: RedisService) {
    @GetMapping("/set")
    fun setValue(@RequestParam key: String, @RequestParam value: String) {
        service.setValue(key, value)
    }

    @GetMapping("/get")
    fun getValue(@RequestParam key: String): String? {
        return service.getValue(key)
    }
}