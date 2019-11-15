package com.example.demo;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class CafeMessageListener {

    @RabbitListener(queues = "coffee.queue")
    public void receiveMessage(final Message message) {
        System.out.println(message);
    }
}