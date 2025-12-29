package edu.raijin.finance.finance.infrastructure.adapter.out.messaging.topic;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import edu.raijin.commons.infrastructure.adapter.messaging.event.finance.ExpenseEvent;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.finance.finance.domain.model.Expense;
import edu.raijin.finance.finance.domain.port.messaging.CreatedExpensePublisherPort;
import edu.raijin.finance.finance.domain.port.messaging.DeletedExpensePublisherPort;
import edu.raijin.finance.finance.domain.port.messaging.UpdatedExpensePublisherPort;
import edu.raijin.finance.finance.infrastructure.adapter.out.messaging.mapper.ExpenseEventMapper;
import edu.raijin.finance.shared.infrastructure.adapter.config.property.KafkaTopicsProperty;
import lombok.RequiredArgsConstructor;

@Adapter
@Component
@RequiredArgsConstructor
public class ExpenseKafkaPublisherAdapter
        implements CreatedExpensePublisherPort, UpdatedExpensePublisherPort, DeletedExpensePublisherPort {

    private final ExpenseEventMapper mapper;
    private final KafkaTopicsProperty kafkaTopics;
    private final KafkaTemplate<String, ExpenseEvent> kafkaTemplate;

    private void publishExpense(String action, Expense expense) {
        ExpenseEvent event = mapper.toEvent(expense);
        kafkaTemplate.send(kafkaTopics.expenseCommandsTopic(), action, event);
    }

    @Override
    public void publishCreatedExpense(Expense expense) {
        publishExpense("create", expense);
    }

    @Override
    public void publishUpdatedExpense(Expense expense) {
        publishExpense("update", expense);
    }

    @Override
    public void publishDeletedExpense(Expense expense) {
        publishExpense("delete", expense);
    }
}
