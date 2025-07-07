package com.thaidc.kafka_schema_registry.producer;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import com.thaidc.kafka_schema_registry.dto.Employee;
import com.thaidc.kafka_schema_registry.mapper.EmployeeMapper;
import com.thaidc.kafka_schema_registry.validation.ValidatedEmployee;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KafkaAvroProducer {
    @Value("${topic.name}")
    private String topicName;

    private final KafkaTemplate<String, Employee> template;
    private final Validator validator;
    private final EmployeeMapper employeeMapper;

    KafkaAvroProducer(KafkaTemplate<String, Employee> template, Validator validator,
            EmployeeMapper employeeMapper) {
        this.template = template;
        this.validator = validator;
        this.employeeMapper = employeeMapper;
    }

    public void send(Employee employee) {
        ValidatedEmployee validated = employeeMapper.toValidated(employee);

        Set<ConstraintViolation<ValidatedEmployee>> violations = validator.validate(validated);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        CompletableFuture<SendResult<String, Employee>> future =
                template.send(topicName, UUID.randomUUID().toString(), employee);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("Send message=[" + employee + "] with offset=["
                        + result.getRecordMetadata().offset() + "]");
            } else {
                log.info("Unable to send message=[" + employee + "] due to: " + ex.getMessage());
            }
        });
    }
}
