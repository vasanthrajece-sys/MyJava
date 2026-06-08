package com.vasanth.kafka.consumer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerStatusController {

    @GetMapping("/status")
    public String status() {
        return "Kafka consumer service is running";
    }
}
