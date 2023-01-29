package sample04_spring.demo018_rabbitmq

import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


/*
 1. install erlang
    https://www.erlang.org/patches/otp-25.1.1


 2. install rabbitmq
    https://www.rabbitmq.com/download.html


 */
@RestController
class Demo018PublisherController(
    val rabbitTemplate: RabbitTemplate
) {

    /**
     * http://localhost:8080/api/demo018?msg=hi
     */
    @GetMapping("/api/demo018")
    fun getCacheValue(
        @RequestParam("msg") msg: String
    ): String {
        rabbitTemplate.convertAndSend(
            Demo018PublisherConfig.exchangeName,
            Demo018PublisherConfig.routingKey,
            msg
        )
        return "sending : $msg"
    }

}
