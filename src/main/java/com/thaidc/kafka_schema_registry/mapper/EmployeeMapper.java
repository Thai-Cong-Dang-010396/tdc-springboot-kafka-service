package com.thaidc.kafka_schema_registry.mapper;

import org.mapstruct.Mapper;
import com.thaidc.kafka_schema_registry.dto.Employee;
import com.thaidc.kafka_schema_registry.validation.ValidatedEmployee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    ValidatedEmployee toValidated(Employee employee);

    default String map(CharSequence value) {
        return value == null ? null : value.toString();
    }
}
