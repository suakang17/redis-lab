package com.example.redislab

import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class RedisController(
    private val redisService: RedisService,
    private val httpServletResponse: HttpServletResponse
) {

    @PostMapping("/write")
    fun write(@RequestParam key: String, @RequestParam value: String) {
        println("write-OK");
        redisService.writeData(key, value)
    }

    @GetMapping("/read")
    fun read(@RequestParam key: String): String? {
        return redisService.readData(key)
    }

    @GetMapping("/")
    fun default() {
        println("OK");
    }
}