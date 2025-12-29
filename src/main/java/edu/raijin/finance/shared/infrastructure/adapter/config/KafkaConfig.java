package edu.raijin.finance.shared.infrastructure.adapter.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import edu.raijin.finance.shared.infrastructure.adapter.config.property.KafkaTopicsProperty;

@Configuration
public class KafkaConfig {

    @Bean
    NewTopic contractTopic(KafkaTopicsProperty kafkaTopics) {
        return TopicBuilder.name(kafkaTopics.contractCommandsTopic()).build();
    }

    @Bean
    NewTopic incomeTopic(KafkaTopicsProperty kafkaTopics) {
        return TopicBuilder.name(kafkaTopics.incomeCommandsTopic()).build();
    }

    @Bean
    NewTopic expenseTopic(KafkaTopicsProperty kafkaTopics) {
        return TopicBuilder.name(kafkaTopics.expenseCommandsTopic()).build();
    }

    @Bean
    NewTopic payrollTopic(KafkaTopicsProperty kafkaTopics) {
        return TopicBuilder.name(kafkaTopics.payrollCommandsTopic()).build();
    }

    @Bean
    NewTopic bonusTopic(KafkaTopicsProperty kafkaTopics) {
        return TopicBuilder.name(kafkaTopics.bonusCommandsTopic()).build();
    }

    @Bean
    NewTopic discountTopic(KafkaTopicsProperty kafkaTopics) {
        return TopicBuilder.name(kafkaTopics.discountCommandsTopic()).build();
    }

    @Bean
    NewTopic suspensionTopic(KafkaTopicsProperty kafkaTopics) {
        return TopicBuilder.name(kafkaTopics.suspensionCommandsTopic()).build();
    }
}