package sample04spring.demo004_scheduler

import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class Demo003Service {

    fun task() {
        if (false) println("Demo003Service task exe time : ${LocalDateTime.now()}")
    }

}