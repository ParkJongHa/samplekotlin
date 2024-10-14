package sample04_spring.demo001_default

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

/*
default server
    kotlin
    java 21 (LTS)
    spring boot 3.3
 */
@RestController
class Demo001Controller {

    @GetMapping("/api/demo001")
    fun getServerTime(): String {
        return "Server return time : ${LocalDateTime.now()}"
    }

}