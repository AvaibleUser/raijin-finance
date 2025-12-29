package edu.raijin.finance.salary.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.commons.util.exception.ValueNotFoundException;
import edu.raijin.finance.salary.domain.model.Discount;
import edu.raijin.finance.salary.domain.port.messaging.CreatedDiscountPublisherPort;
import edu.raijin.finance.salary.domain.port.persistence.RegisterDiscountPort;
import edu.raijin.finance.salary.domain.usecase.CreateDiscountUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateDiscountService implements CreateDiscountUseCase {

    private final RegisterDiscountPort register;
    private final CreatedDiscountPublisherPort publisher;

    @Override
    @Transactional
    public Discount create(UUID employeeId, Discount discount) {
        if (!register.existsContractedEmployee(employeeId)) {
            throw new ValueNotFoundException("El empleado no se encuentra registrado");
        }
        discount.checkValidRegistration();
        Discount created = register.register(employeeId, discount);

        publisher.publishCreatedDiscount(created);
        return created;
    }
}
