package eu.morozik.historicalplaces.mq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
@Slf4j
public class Producer {

    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbit.exchange}")
    private String exchange;

    @Value("${rabbit.routing-key}")
    private String routingKey;

    @PostConstruct
    public void produce() {
        log.info("Hello form produce ");
        rabbitTemplate.convertAndSend(exchange, routingKey, "Hello form produce1");
        rabbitTemplate.convertAndSend(exchange, routingKey, "Hello form produce2");

    }
}
