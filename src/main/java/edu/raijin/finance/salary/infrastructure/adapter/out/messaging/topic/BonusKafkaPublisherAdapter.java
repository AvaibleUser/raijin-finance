package edu.raijin.finance.salary.infrastructure.adapter.out.messaging.topic;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import edu.raijin.commons.infrastructure.adapter.messaging.event.finance.BonusEvent;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.finance.salary.domain.model.Bonus;
import edu.raijin.finance.salary.domain.port.messaging.CreatedBonusPublisherPort;
import edu.raijin.finance.salary.domain.port.messaging.DeletedBonusPublisherPort;
import edu.raijin.finance.salary.domain.port.messaging.UpdatedBonusPublisherPort;
import edu.raijin.finance.salary.infrastructure.adapter.out.messaging.mapper.BonusEventMapper;
import edu.raijin.finance.shared.infrastructure.adapter.config.property.KafkaTopicsProperty;
import lombok.RequiredArgsConstructor;

@Adapter
@Component
@RequiredArgsConstructor
public class BonusKafkaPublisherAdapter
        implements CreatedBonusPublisherPort, UpdatedBonusPublisherPort, DeletedBonusPublisherPort {

    private final BonusEventMapper mapper;
    private final KafkaTopicsProperty kafkaTopics;
    private final KafkaTemplate<String, BonusEvent> kafkaTemplate;

    private void publishBonus(String action, Bonus bonus) {
        BonusEvent event = mapper.toEvent(bonus);
        kafkaTemplate.send(kafkaTopics.bonusCommandsTopic(), action, event);
    }

    @Override
    public void publishCreatedBonus(Bonus bonus) {
        publishBonus("create", bonus);
    }

    @Override
    public void publishUpdatedBonus(Bonus bonus) {
        publishBonus("update", bonus);
    }

    @Override
    public void publishDeletedBonus(Bonus bonus) {
        publishBonus("delete", bonus);
    }
}
