package edu.raijin.finance.project.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.commons.util.exception.RequestConflictException;
import edu.raijin.finance.project.domain.model.Member;
import edu.raijin.finance.project.domain.port.persistence.AssignMemberPort;
import edu.raijin.finance.project.domain.usecase.AssignMemberUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AssignMemberService implements AssignMemberUseCase {

    private final AssignMemberPort assign;

    @Override
    @Transactional
    public void assign(Member member) {
        if (assign.exists(member.getProjectId(), member.getEmployeeId())) {
            throw new RequestConflictException("El miembro ya se encuentra registrado en el proyecto");
        }
        member.checkValidRegistration();
        assign.assign(member.getProjectId(), member.getEmployeeId());
    }
}
