package edu.raijin.finance.employee.domain.usecase;

import java.util.UUID;

import edu.raijin.commons.util.annotation.UseCase;

@UseCase
public interface DeleteEmployeeUseCase {

    void delete(UUID employeeId);
}