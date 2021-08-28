package br.com.mekyei.ms.email.configurations.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfiguration {
    /* Nome da fila obtido via namespace do application.properties */
    @Value("${spring.rabbitmq.template.default-receive-queue}")
    private String queue;

    /**
     * Cria a fila
     *
     * @return A fila criada.
     */
    @Bean
    public Queue queue() {
        return new Queue(queue, true);
    }

    /**
     * Cria um conversor de mensagem.
     *
     * @return O conversor.
     */
    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
