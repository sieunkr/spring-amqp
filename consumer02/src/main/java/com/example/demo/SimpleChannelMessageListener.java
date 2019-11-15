package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SimpleChannelMessageListener implements ChannelAwareMessageListener {

    private final CafeService cafeService;

    public SimpleChannelMessageListener(CafeService cafeService) {
        this.cafeService = cafeService;
    }

    @Override
    public void onMessage(Message message, Channel channel) throws IOException {

        System.out.println(Thread.currentThread().getName() + ":onMessage");

        ObjectMapper objectMapper = new ObjectMapper();
        CustomMessage c = objectMapper.readValue(new String(message.getBody()), CustomMessage.class);

        cafeService.accept(c);
    }
}