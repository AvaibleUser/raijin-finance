package edu.raijin.finance.project.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.finance.project.domain.model.Member;
import edu.raijin.finance.project.domain.port.persistence.UnassignMemberPort;
import edu.raijin.finance.project.domain.usecase.UnassignMemberUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UnassignMemberService implements UnassignMemberUseCase {

    private final UnassignMemberPort unassign;

    @Override
    @Transactional
    public void unassign(Member member) {
        if (!unassign.exists(member.getProjectId(), member.getEmployeeId())) {
            return;
        }
        unassign.unassign(member.getProjectId(), member.getEmployeeId());
    }
}
