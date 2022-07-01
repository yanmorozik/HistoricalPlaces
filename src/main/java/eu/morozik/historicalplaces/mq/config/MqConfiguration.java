package eu.morozik.historicalplaces.mq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqConfiguration {

    public static final String QUEUE = "queue";

    @Bean
    public Queue queue() {
        return new Queue(QUEUE);
    }
}
