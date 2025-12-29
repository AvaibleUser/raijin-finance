package edu.raijin.finance.salary.infrastructure.adapter.out.messaging.topic;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import edu.raijin.commons.infrastructure.adapter.messaging.event.finance.DiscountEvent;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.finance.salary.domain.model.Discount;
import edu.raijin.finance.salary.domain.port.messaging.CreatedDiscountPublisherPort;
import edu.raijin.finance.salary.domain.port.messaging.DeletedDiscountPublisherPort;
import edu.raijin.finance.salary.domain.port.messaging.UpdatedDiscountPublisherPort;
import edu.raijin.finance.salary.infrastructure.adapter.out.messaging.mapper.DiscountEventMapper;
import edu.raijin.finance.shared.infrastructure.adapter.config.property.KafkaTopicsProperty;
import lombok.RequiredArgsConstructor;

@Adapter
@Component
@RequiredArgsConstructor
public class DiscountKafkaPublisherAdapter
        implements CreatedDiscountPublisherPort, UpdatedDiscountPublisherPort, DeletedDiscountPublisherPort {

    private final DiscountEventMapper mapper;
    private final KafkaTopicsProperty kafkaTopics;
    private final KafkaTemplate<String, DiscountEvent> kafkaTemplate;

    private void publishDiscount(String action, Discount discount) {
        DiscountEvent event = mapper.toEvent(discount);
        kafkaTemplate.send(kafkaTopics.discountCommandsTopic(), action, event);
    }

    @Override
    public void publishCreatedDiscount(Discount discount) {
        publishDiscount("create", discount);
    }

    @Override
    public void publishUpdatedDiscount(Discount discount) {
        publishDiscount("update", discount);
    }

    @Override
    public void publishDeletedDiscount(Discount discount) {
        publishDiscount("delete", discount);
    }
}
