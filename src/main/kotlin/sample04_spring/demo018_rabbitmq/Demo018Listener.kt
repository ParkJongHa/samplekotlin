package sample04_spring.demo018_rabbitmq

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

@Component
class Demo018Listener {

    private val log: Logger = LoggerFactory.getLogger(Demo018Listener::class.java)

    @RabbitListener(queues = ["demo.queue"])
    fun listen(msg: org.springframework.amqp.core.Message) {
        log.info("Demo006Listener listen")
        log.info("msg.messageProperties.headers.entries")

        msg.messageProperties.headers.entries.forEach {
            log.info("\t${it.key} : ${it.value}")
        }

        log.info("msg.body")
        log.info("\t${String(msg.body)}")
    }

}