package sample04_spring.demo016_scheduler

import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class Demo016Service {

    fun task() {
        if (false) println("Demo016Service task exe time : ${LocalDateTime.now()}")
    }

}