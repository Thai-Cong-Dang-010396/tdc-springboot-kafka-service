package com.thaidc.kafka_schema_registry.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.thaidc.kafka_schema_registry.dto.Employee;
import com.thaidc.kafka_schema_registry.producer.KafkaAvroProducer;


@RequestMapping("/api/kafka")
@RestController
public class EventController {

    private final KafkaAvroProducer producer;

    EventController(KafkaAvroProducer producer) {
        this.producer = producer;
    }

    @PostMapping("/publish")
    public ResponseEntity<String> sendMessage(@RequestBody Employee employee) {
        try {
            producer.send(employee);
            return ResponseEntity.ok("message published successfully..");
        } catch (Exception e) {
            String errorMessage = "Failed to publish message: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

}
