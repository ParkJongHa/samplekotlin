package com.example.demo.sample006_rabbitmq

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

@Component
class Sample006Listener {

    private val log: Logger = LoggerFactory.getLogger(Sample006Listener::class.java)

    @RabbitListener(queues = ["sample.queue"])
    fun listen(msg: org.springframework.amqp.core.Message) {
        log.info("Sample006Listener listen")
        log.info("msg.messageProperties.headers.entries")

        msg.messageProperties.headers.entries.forEach {
            log.info("\t${it.key} : ${it.value}")
        }

        log.info("msg.body")
        log.info("\t${String(msg.body)}")
    }

}