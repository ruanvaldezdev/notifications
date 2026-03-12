package com.example.sms.receiver;

import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.context.annotation.Bean;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;


@Configuration
public class ConfigRabbitMq {

@Value("fila.sms")
private String queue;

@Bean
public Queue queue(){
    return new Queue(queue,true);
}

@Bean
public Jackson2JsonMessageConverter messageConverter(){
    ObjectMapper objectMapper = new ObjectMapper();
    return new Jackson2JsonMessageConverter(objectMapper);
}


}
