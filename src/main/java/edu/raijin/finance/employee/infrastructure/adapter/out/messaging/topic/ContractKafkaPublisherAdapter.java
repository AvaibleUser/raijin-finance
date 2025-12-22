package edu.raijin.finance.employee.infrastructure.adapter.out.messaging.topic;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import edu.raijin.commons.infrastructure.adapter.messaging.event.finance.ContractEvent;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.finance.employee.domain.model.Contract;
import edu.raijin.finance.employee.domain.port.messaging.CreatedContractPublisherPort;
import edu.raijin.finance.employee.domain.port.messaging.DeletedContractPublisherPort;
import edu.raijin.finance.employee.domain.port.messaging.UpdatedContractPublisherPort;
import edu.raijin.finance.employee.infrastructure.adapter.out.messaging.mapper.ContractEventMapper;
import edu.raijin.finance.shared.infrastructure.adapter.config.property.KafkaTopicsProperty;
import lombok.RequiredArgsConstructor;

@Adapter
@Component
@RequiredArgsConstructor
public class ContractKafkaPublisherAdapter
        implements CreatedContractPublisherPort, UpdatedContractPublisherPort, DeletedContractPublisherPort {

    private final ContractEventMapper mapper;
    private final KafkaTopicsProperty kafkaTopics;
    private final KafkaTemplate<String, ContractEvent> kafkaTemplate;

    private void publishContract(String action, Contract contract) {
        ContractEvent event = mapper.toEvent(contract);
        kafkaTemplate.send(kafkaTopics.contractCommandsTopic(), action, event);
    }

    @Override
    public void publishCreatedContract(Contract contract) {
        publishContract("create", contract);
    }

    @Override
    public void publishUpdatedContract(Contract contract) {
        publishContract("update", contract);
    }

    @Override
    public void publishDeletedContract(Contract contract) {
        publishContract("delete", contract);
    }
}
