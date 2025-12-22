package edu.raijin.finance.project.domain.port.persistence;

import java.util.UUID;

import edu.raijin.commons.util.annotation.Port;

@Port
public interface AssignMemberPort {

    boolean exists(UUID projectId, UUID employeeId);

    void assign(UUID projectId, UUID employeeId);
}
