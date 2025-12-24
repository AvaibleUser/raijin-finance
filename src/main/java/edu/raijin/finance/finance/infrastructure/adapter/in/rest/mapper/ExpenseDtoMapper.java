package edu.raijin.finance.finance.infrastructure.adapter.in.rest.mapper;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.finance.finance.domain.model.Expense;
import edu.raijin.finance.finance.infrastructure.adapter.in.rest.dto.expense.AddExpenseDto;
import edu.raijin.finance.finance.infrastructure.adapter.in.rest.dto.expense.ExpenseDto;

@Adapter
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE, nullValueCheckStrategy = ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ExpenseDtoMapper {

    @Mapping(target = "employeeId", ignore = true)
    Expense toDomain(AddExpenseDto dto);

    ExpenseDto toDto(Expense domain);
}
