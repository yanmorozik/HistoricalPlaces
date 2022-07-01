package eu.morozik.historicalplaces.mq;

import eu.morozik.historicalplaces.mq.config.MqConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
@Slf4j
public class Producer {

    private final AmqpTemplate amqpTemplate;

    @PostConstruct
    public void produce() {
        log.info("Hello form produce");
        amqpTemplate.convertAndSend(MqConfiguration.QUEUE, "Hello form produce1");
        amqpTemplate.convertAndSend(MqConfiguration.QUEUE, "Hello form produce2");
    }
}
