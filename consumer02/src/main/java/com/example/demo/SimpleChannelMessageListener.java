package com.example.demo;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

@Component
public class SimpleChannelMessageListener implements ChannelAwareMessageListener {

    private final CafeService cafeService;

    public SimpleChannelMessageListener(CafeService cafeService) {
        this.cafeService = cafeService;
    }

    @Override
    public void onMessage(Message message, Channel channel) {

        System.out.println(Thread.currentThread().getName() + ":onMessage");
        cafeService.accept();
        //channel.basicAck();
    }
}
