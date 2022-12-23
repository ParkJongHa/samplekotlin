package com.example.demo.sample005_quartz

import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class Sample005QuartzService {

    fun execute() {
        if (false) println("Sample005QuartzService execute time : ${LocalDateTime.now()}")
    }
}