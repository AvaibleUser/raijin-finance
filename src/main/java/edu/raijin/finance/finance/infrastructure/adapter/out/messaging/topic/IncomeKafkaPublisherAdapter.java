package edu.raijin.finance.finance.infrastructure.adapter.out.messaging.topic;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import edu.raijin.commons.infrastructure.adapter.messaging.event.finance.IncomeEvent;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.finance.finance.domain.model.Income;
import edu.raijin.finance.finance.domain.port.messaging.CreatedIncomePublisherPort;
import edu.raijin.finance.finance.domain.port.messaging.DeletedIncomePublisherPort;
import edu.raijin.finance.finance.domain.port.messaging.UpdatedIncomePublisherPort;
import edu.raijin.finance.finance.infrastructure.adapter.out.messaging.mapper.IncomeEventMapper;
import edu.raijin.finance.shared.infrastructure.adapter.config.property.KafkaTopicsProperty;
import lombok.RequiredArgsConstructor;

@Adapter
@Component
@RequiredArgsConstructor
public class IncomeKafkaPublisherAdapter
        implements CreatedIncomePublisherPort, UpdatedIncomePublisherPort, DeletedIncomePublisherPort {

    private final IncomeEventMapper mapper;
    private final KafkaTopicsProperty kafkaTopics;
    private final KafkaTemplate<String, IncomeEvent> kafkaTemplate;

    private void publishIncome(String action, Income income) {
        IncomeEvent event = mapper.toEvent(income);
        kafkaTemplate.send(kafkaTopics.incomeCommandsTopic(), action, event);
    }

    @Override
    public void publishCreatedIncome(Income income) {
        publishIncome("create", income);
    }

    @Override
    public void publishUpdatedIncome(Income income) {
        publishIncome("update", income);
    }

    @Override
    public void publishDeletedIncome(Income income) {
        publishIncome("delete", income);
    }
}
