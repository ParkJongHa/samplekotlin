package com.example.demo.sample006_rabbitmq

import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.context.annotation.Bean

class Sample006PublisherConfig {

    companion object {
        const val exchangeName: String = "sample.exchange"
        const val routingKey: String = "sample.routingkey"
    }
    private val queueName: String = "sample.queue"

    @Bean
    fun topicExchange(): TopicExchange {
        return TopicExchange(exchangeName)
    }

    @Bean
    fun binding(queue: Queue, topicExchange: TopicExchange): Binding {
        return BindingBuilder.bind(queue).to(topicExchange).with(routingKey)
    }

    @Bean
    fun queue(): Queue {
        return Queue(queueName)
    }

    @Bean
    fun rabbitTemplate(connectionFactory: ConnectionFactory, messageConverter: MessageConverter): RabbitTemplate {
        return RabbitTemplate(connectionFactory).apply {
            this.messageConverter = Jackson2JsonMessageConverter()
        }
    }

}