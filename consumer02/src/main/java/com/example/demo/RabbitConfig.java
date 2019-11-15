package com.example.demo;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    private final SimpleChannelMessageListener setMessageListener;

    private static final String EXCHANGE_NAME = "cafe.topic";
    private static final String QUEUE_NAME = "coffee.queue";
    private static final String ROUTING_KEY = "order.coffee.#";

    public RabbitConfig(SimpleChannelMessageListener setMessageListener) {
        this.setMessageListener = setMessageListener;
    }

    @Bean
    Queue queue() {
        return new Queue(QUEUE_NAME, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }

    @Bean
    MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean("simpleMessageListenerContainer")
    public SimpleMessageListenerContainer simpleMessageListenerContainer(ConnectionFactory connectionFactory){

        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(QUEUE_NAME);
        //simpleMessageListenerContainer.setConcurrency(10);
        simpleMessageListenerContainer.setMessageListener(setMessageListener);
        return simpleMessageListenerContainer;
    }

}