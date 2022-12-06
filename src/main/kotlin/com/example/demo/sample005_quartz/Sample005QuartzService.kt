package com.example.demo.sample005_quartz

import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class Sample005QuartzService {

    fun execute() {
        println("Sample005QuartzService execute time : ${LocalDateTime.now()}")
    }
}