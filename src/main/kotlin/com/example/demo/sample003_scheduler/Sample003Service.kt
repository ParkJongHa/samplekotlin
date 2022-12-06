package com.example.demo.sample003_scheduler

import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class Sample003Service {

    fun task() {
        println("Sample003Service task exe time : ${LocalDateTime.now()}")
    }

}