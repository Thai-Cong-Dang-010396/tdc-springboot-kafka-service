package com.thaidc.kafka_schema_registry.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.thaidc.kafka_schema_registry.dto.Employee;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KafkaAvroConsumer {

    @KafkaListener(topics = "${topic.name}")
    public void read(ConsumerRecord<String, Employee> consumerRecord) {
        String key = consumerRecord.key();
        Employee employee = consumerRecord.value();
        log.info("Avro message received for key: " + key + " value: " + employee.toString());
    }
}
