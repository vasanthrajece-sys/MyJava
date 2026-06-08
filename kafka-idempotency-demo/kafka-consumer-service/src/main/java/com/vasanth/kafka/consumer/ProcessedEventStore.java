package com.vasanth.kafka.consumer;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

@Component
public class ProcessedEventStore {
    private final Set<String> processedEventIds = ConcurrentHashMap.newKeySet();

    public boolean isNewEvent(String eventId) {
        return processedEventIds.add(eventId);
    }
}
