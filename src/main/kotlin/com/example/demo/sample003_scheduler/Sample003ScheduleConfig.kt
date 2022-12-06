package com.example.demo.sample003_scheduler

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.TaskScheduler
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler
import org.springframework.scheduling.support.CronTrigger

@Configuration
class Sample003ScheduleConfig(
    val sample003Service: Sample003Service
) {

    @Bean
    fun taskScheduler(): TaskScheduler {
        val concurrentTaskScheduler = ConcurrentTaskScheduler()

        concurrentTaskScheduler.schedule({
            sample003Service.task()
        }, CronTrigger("0/10 * * * * *"))

        return concurrentTaskScheduler
    }

}