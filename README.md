# Kafka Spring Boot Producer/Consumer

A simple microservice project demonstrating how to use **Apache Kafka** with **Spring Boot**. This project includes a Kafka producer and consumer, containerized with Docker Compose.

---

## 🚀 Features

- Kafka Producer API using Spring Boot
- Kafka Consumer with auto-listening
- Docker Compose setup for Kafka, Zookeeper, Kafdrop, and Schema Registry
- JSON and Avro serialization support
- REST endpoint to publish messages

---

## 🧰 Tech Stack

- Java 17+
- Spring Boot 3.x
- Apache Kafka
- Confluent Schema Registry
- Docker & Docker Compose

---

## 📦 Getting Started

### Prerequisites

- Docker & Docker Compose
- Java 17+
- Maven

### Clone the Repository

```bash
git clone https://github.com/Thai-Cong-Dang-010396/tdc-springboot-kafka-service.git
cd tdc-springboot-kafka-service
```

### Start Kafka Environment
```bash
docker-compose up -d
```

### Run the Spring Boot App
```bash
./mvnw spring-boot:run
```