package edu.raijin.finance.employee.infrastructure.adapter.out.persistence.mapper;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.finance.employee.domain.model.Employee;
import edu.raijin.finance.employee.infrastructure.adapter.out.persistence.entity.EmployeesEntity;

@Adapter
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployeeEntityMapper {

    Employee toDomain(EmployeesEntity entity);

    EmployeesEntity toEntity(Employee domain);
}
