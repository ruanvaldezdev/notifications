package api.api.amqp;

import java.util.HashMap;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {

    public static final String EXCHANGE = "notificacoes.direct";


    @Bean public Queue queueEmail() { return new Queue("fila.email"); }
    @Bean public Queue queueSms() { return new Queue("fila.sms"); }
    @Bean public Queue queuePush() { return new Queue("fila.push"); }

    @Bean
    public Binding bindEmail(Queue queueEmail, DirectExchange exchange) {
        return BindingBuilder.bind(queueEmail).to(exchange).with("email");
    }
    @Bean
    public Binding bindSms(Queue queueSms, DirectExchange exchange) {
        return BindingBuilder.bind(queueSms).to(exchange).with("sms");
    }
    @Bean
    public Binding bindPush(Queue queuePush, DirectExchange exchange) {
        return BindingBuilder.bind(queuePush).to(exchange).with("push");
    }

    
    @Bean
    public RabbitAdmin criaRabbitAdmin(ConnectionFactory conn) {
        return new RabbitAdmin(conn);
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> inicializaAdmin(RabbitAdmin rabbitAdmin){
        return event -> rabbitAdmin.initialize();
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter(){
        return  new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
                                         Jackson2JsonMessageConverter messageConverter){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return  rabbitTemplate;
    }

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(EXCHANGE);
        }
    @Bean
    public Queue priorityQueue() {
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-max-priority", 10); // Define o intervalo de prioridade (0 a 10)
        return new Queue("priority_queue", true, false, false, arguments);
    }


}
