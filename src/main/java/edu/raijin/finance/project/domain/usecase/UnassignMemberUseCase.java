package edu.raijin.finance.project.domain.usecase;

import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.finance.project.domain.model.Member;

@UseCase
public interface UnassignMemberUseCase {

    void unassign(Member member);
}
