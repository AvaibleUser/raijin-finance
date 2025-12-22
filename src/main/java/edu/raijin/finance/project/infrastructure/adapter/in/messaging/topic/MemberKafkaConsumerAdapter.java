package edu.raijin.finance.project.infrastructure.adapter.in.messaging.topic;

import static org.springframework.kafka.support.KafkaHeaders.RECEIVED_KEY;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import edu.raijin.commons.infrastructure.adapter.messaging.event.scrum.MemberEvent;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.finance.project.domain.model.Member;
import edu.raijin.finance.project.domain.usecase.AssignMemberUseCase;
import edu.raijin.finance.project.domain.usecase.UnassignMemberUseCase;
import edu.raijin.finance.project.infrastructure.adapter.in.messaging.mapper.MemberEventMapper;
import lombok.RequiredArgsConstructor;

@Adapter
@Component
@RequiredArgsConstructor
public class MemberKafkaConsumerAdapter {

    private final AssignMemberUseCase assign;
    private final UnassignMemberUseCase unassign;
    private final MemberEventMapper mapper;

    private void consumeCreatedMember(Member member) {
        assign.assign(member);
    }

    private void consumeDeletedMember(Member member) {
        unassign.unassign(member);
    }

    @KafkaListener(topics = "${kafka.topics.member-commands.topic}", properties = "${kafka.topics.member-commands.properties", groupId = "finance-service")
    public void consumeMemberEvent(@Payload MemberEvent event, @Header(RECEIVED_KEY) String key) {
        Member member = mapper.toDomain(event);
        switch (key) {
            case "create" -> consumeCreatedMember(member);
            case "delete" -> consumeDeletedMember(member);
        }
    }
}