package sample04_spring.demo016_scheduler

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.TaskScheduler
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler
import org.springframework.scheduling.support.CronTrigger

@Configuration
class Demo016ScheduleConfig(
    val demo016Service: Demo016Service
) {

    @Bean
    fun taskScheduler(): TaskScheduler {
        val concurrentTaskScheduler = ConcurrentTaskScheduler()

        concurrentTaskScheduler.schedule({
            demo016Service.task()
        }, CronTrigger("0/10 * * * * *"))

        return concurrentTaskScheduler
    }

}