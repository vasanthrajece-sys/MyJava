package com.vasanth.kafka.consumer;

import com.vasanth.kafka.common.OrderCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {
    private static final Logger log = LoggerFactory.getLogger(OrderConsumer.class);

    private final ProcessedEventStore processedEventStore;

    public OrderConsumer(ProcessedEventStore processedEventStore) {
        this.processedEventStore = processedEventStore;
    }

    @KafkaListener(topics = "${app.kafka.order-topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(OrderCreatedEvent event) {
        if (!processedEventStore.isNewEvent(event.getEventId())) {
            log.warn("Duplicate event skipped. eventId={}", event.getEventId());
            return;
        }

        log.info("Processing new order event: {}", event);
    }
}
