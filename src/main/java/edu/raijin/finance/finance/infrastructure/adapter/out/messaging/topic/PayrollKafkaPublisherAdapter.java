package edu.raijin.finance.finance.infrastructure.adapter.out.messaging.topic;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import edu.raijin.commons.infrastructure.adapter.messaging.event.finance.PayrollEvent;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.finance.finance.domain.model.Payroll;
import edu.raijin.finance.finance.domain.port.messaging.CreatedPayrollPublisherPort;
import edu.raijin.finance.finance.domain.port.messaging.DeletedPayrollPublisherPort;
import edu.raijin.finance.finance.domain.port.messaging.UpdatedPayrollPublisherPort;
import edu.raijin.finance.finance.infrastructure.adapter.out.messaging.mapper.PayrollEventMapper;
import edu.raijin.finance.shared.infrastructure.adapter.config.property.KafkaTopicsProperty;
import lombok.RequiredArgsConstructor;

@Adapter
@Component
@RequiredArgsConstructor
public class PayrollKafkaPublisherAdapter
        implements CreatedPayrollPublisherPort, UpdatedPayrollPublisherPort, DeletedPayrollPublisherPort {

    private final PayrollEventMapper mapper;
    private final KafkaTopicsProperty kafkaTopics;
    private final KafkaTemplate<String, PayrollEvent> kafkaTemplate;

    private void publishPayroll(String action, Payroll payroll) {
        PayrollEvent event = mapper.toEvent(payroll);
        kafkaTemplate.send(kafkaTopics.payrollCommandsTopic(), action, event);
    }

    @Override
    public void publishCreatedPayroll(Payroll payroll) {
        publishPayroll("create", payroll);
    }

    @Override
    public void publishUpdatedPayroll(Payroll payroll) {
        publishPayroll("update", payroll);
    }

    @Override
    public void publishDeletedPayroll(Payroll payroll) {
        publishPayroll("delete", payroll);
    }
}
