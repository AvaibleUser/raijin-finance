package edu.raijin.finance.salary.infrastructure.adapter.out.messaging.topic;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import edu.raijin.commons.infrastructure.adapter.messaging.event.finance.SuspensionEvent;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.finance.salary.domain.model.Suspension;
import edu.raijin.finance.salary.domain.port.messaging.CreatedSuspensionPublisherPort;
import edu.raijin.finance.salary.domain.port.messaging.DeletedSuspensionPublisherPort;
import edu.raijin.finance.salary.domain.port.messaging.UpdatedSuspensionPublisherPort;
import edu.raijin.finance.salary.infrastructure.adapter.out.messaging.mapper.SuspensionEventMapper;
import edu.raijin.finance.shared.infrastructure.adapter.config.property.KafkaTopicsProperty;
import lombok.RequiredArgsConstructor;

@Adapter
@Component
@RequiredArgsConstructor
public class SuspensionKafkaPublisherAdapter
        implements CreatedSuspensionPublisherPort, UpdatedSuspensionPublisherPort, DeletedSuspensionPublisherPort {

    private final SuspensionEventMapper mapper;
    private final KafkaTopicsProperty kafkaTopics;
    private final KafkaTemplate<String, SuspensionEvent> kafkaTemplate;

    private void publishSuspension(String action, Suspension suspension) {
        SuspensionEvent event = mapper.toEvent(suspension);
        kafkaTemplate.send(kafkaTopics.suspensionCommandsTopic(), action, event);
    }

    @Override
    public void publishCreatedSuspension(Suspension suspension) {
        publishSuspension("create", suspension);
    }

    @Override
    public void publishUpdatedSuspension(Suspension suspension) {
        publishSuspension("update", suspension);
    }

    @Override
    public void publishDeletedSuspension(Suspension suspension) {
        publishSuspension("delete", suspension);
    }
}
