package edu.raijin.finance.finance.application.service;

import static java.util.Objects.isNull;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.commons.util.exception.BadRequestException;
import edu.raijin.commons.util.exception.ValueNotFoundException;
import edu.raijin.finance.finance.domain.model.Expense;
import edu.raijin.finance.finance.domain.port.messaging.CreatedExpensePublisherPort;
import edu.raijin.finance.finance.domain.port.persistence.RegisterExpensePort;
import edu.raijin.finance.finance.domain.usecase.CreateExpenseUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateExpenseService implements CreateExpenseUseCase {

    private final RegisterExpensePort register;
    private final CreatedExpensePublisherPort publisher;

    @Override
    @Transactional
    public Expense create(UUID projectId, UUID employeeId, Expense expense) {
        if (!isNull(projectId) && !register.existsProject(projectId)) {
            throw new ValueNotFoundException("El proyecto no se encuentra registrado");
        }
        if (!isNull(employeeId)) {
            if (!register.existsEmployee(employeeId)) {
                throw new ValueNotFoundException("El empleado no se encuentra registrado");
            }
            if (isNull(projectId)) {
                throw new BadRequestException("Para crear un gasto para un empleado se requiere el proyecto");
            }
        }
        expense.checkValidRegistration();
        Expense created = register.register(projectId, employeeId, expense);

        publisher.publishCreatedExpense(created);
        return created;
    }
}
