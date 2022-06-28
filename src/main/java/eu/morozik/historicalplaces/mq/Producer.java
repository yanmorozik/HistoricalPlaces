package eu.morozik.historicalplaces.mq;

import eu.morozik.historicalplaces.mq.config.MqConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
@Slf4j
public class Producer {

    private final RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void produce() {
        log.info("Hello form produce");
        rabbitTemplate.convertAndSend(MqConfiguration.QUEUE, "Hello form produce");
    }
}
