package edu.kmaooad.capstone23.members.services.impl;

import edu.kmaooad.capstone23.experts.dal.Expert;
import edu.kmaooad.capstone23.experts.dal.ExpertsRepository;
import edu.kmaooad.capstone23.members.commands.CreateBasicMember;
import edu.kmaooad.capstone23.members.dal.Member;
import edu.kmaooad.capstone23.members.dto.OrgDTO;
import edu.kmaooad.capstone23.members.services.ExpertsService;
import edu.kmaooad.capstone23.orgs.dal.Org;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import java.util.Optional;

@ApplicationScoped
public class ExpertsServiceInternalInterdependentImpl implements ExpertsService {
    @Inject
    ExpertsRepository expertsRepository;

    @Override
    public void createExpertFromMember(CreateBasicMember command, Member member, Optional<OrgDTO> memberOrg) {
        if (memberOrg.isPresent()) {
            Expert expert = new Expert();
            expert.memberId = member.id;
            expert.name = command.getFirstName() + " " + command.getLastName();
            var org = new Org();
            org.id = new ObjectId(memberOrg.get().getId());
            expert.org = org;
            expertsRepository.insert(expert);
        }
    }
}
