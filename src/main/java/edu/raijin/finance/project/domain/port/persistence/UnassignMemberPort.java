package edu.raijin.finance.project.domain.port.persistence;

import java.util.UUID;

import edu.raijin.commons.util.annotation.Port;

@Port
public interface UnassignMemberPort {

    boolean exists(UUID projectId, UUID employeeId);

    void unassign(UUID projectId, UUID employeeId);
}
