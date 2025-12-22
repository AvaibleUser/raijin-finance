package edu.raijin.finance.employee.infrastructure.adapter.in.messaging.topic;

import static org.springframework.kafka.support.KafkaHeaders.RECEIVED_KEY;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import edu.raijin.commons.infrastructure.adapter.messaging.event.identity.UserEvent;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.finance.employee.domain.model.Employee;
import edu.raijin.finance.employee.domain.usecase.CreateEmployeeUseCase;
import edu.raijin.finance.employee.domain.usecase.DeleteEmployeeUseCase;
import edu.raijin.finance.employee.domain.usecase.UpdateEmployeeUseCase;
import edu.raijin.finance.employee.infrastructure.adapter.in.messaging.mapper.UserEventMapper;
import lombok.RequiredArgsConstructor;

@Adapter
@Component
@RequiredArgsConstructor
public class UserKafkaConsumerAdapter {

    private final CreateEmployeeUseCase create;
    private final UpdateEmployeeUseCase update;
    private final DeleteEmployeeUseCase delete;
    private final UserEventMapper mapper;

    private void consumeCreatedUser(Employee employee) {
        create.create(employee);
    }

    private void consumeUpdatedUser(Employee employee) {
        update.update(employee.getId(), employee);
    }

    private void consumeDeletedUser(Employee employee) {
        delete.delete(employee.getId());
    }

    @KafkaListener(topics = "${kafka.topics.user-commands.topic}", properties = "${kafka.topics.user-commands.properties}", groupId = "finance-service")
    public void consumeUserEvent(@Payload UserEvent event, @Header(RECEIVED_KEY) String key) {
        Employee employee = mapper.toDomain(event);
        switch (key) {
            case "create" -> consumeCreatedUser(employee);
            case "update" -> consumeUpdatedUser(employee);
            case "delete" -> consumeDeletedUser(employee);
        }
    }
}