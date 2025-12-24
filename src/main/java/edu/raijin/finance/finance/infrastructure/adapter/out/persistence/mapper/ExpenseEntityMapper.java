package edu.raijin.finance.finance.infrastructure.adapter.out.persistence.mapper;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.finance.finance.domain.model.Expense;
import edu.raijin.finance.finance.infrastructure.adapter.out.persistence.entity.ExpensesEntity;

@Adapter
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ExpenseEntityMapper {

    @Mapping(target = "projectId", source = "project.id")
    @Mapping(target = "employeeId", source = "employee.id")
    Expense toDomain(ExpensesEntity entity);

    @Mapping(target = "project.id", source = "projectId")
    @Mapping(target = "employee.id", source = "employeeId")
    ExpensesEntity toEntity(Expense domain);
}
