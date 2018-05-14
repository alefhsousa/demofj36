package com.alefh.clientdemo.conf;

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConf {

   @Autowired private ConnectionFactory connectionFactory;

   @Value("${spring.rabbitmq.listener.simple.concurrency}")
   private Integer concurrentConsumers;

   @Value("${spring.rabbitmq.listener.simple.max-concurrency}")
   private Integer maxConcurrentConsumers;


    @Bean
    public RabbitTemplate rabbitTemplate(MessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate =
                new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory simpleListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory listenerFactory = new SimpleRabbitListenerContainerFactory();
        listenerFactory.setConnectionFactory(connectionFactory);
        listenerFactory.setConcurrentConsumers(concurrentConsumers);
        listenerFactory.setMaxConcurrentConsumers(maxConcurrentConsumers);
        return listenerFactory;
    }


    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
