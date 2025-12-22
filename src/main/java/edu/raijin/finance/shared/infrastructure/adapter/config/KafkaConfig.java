package edu.raijin.finance.shared.infrastructure.adapter.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import edu.raijin.finance.shared.infrastructure.adapter.config.property.KafkaTopicsProperty;

@Configuration
public class KafkaConfig {

    @Bean
    NewTopic topic(KafkaTopicsProperty kafkaTopics) {
        return TopicBuilder.name(kafkaTopics.contractCommandsTopic()).build();
    }
}