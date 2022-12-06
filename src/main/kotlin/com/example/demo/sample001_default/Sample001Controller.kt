package com.example.demo.sample001_default

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

/*
default server
    kotlin
    java 17 (LTS)
    spring boot 3 (tomcat 10)
 */
@RestController
class Sample001Controller {

    @GetMapping("/api/sample001")
    fun getServerTime(): String {
        return "Server return time : ${LocalDateTime.now()}"
    }

}