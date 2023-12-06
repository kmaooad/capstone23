package edu.kmaooad.capstone23.members.services;

import edu.kmaooad.capstone23.members.commands.CreateBasicMember;
import edu.kmaooad.capstone23.members.dal.Member;
import edu.kmaooad.capstone23.members.dto.OrgDTO;

import java.util.Optional;

public interface ExpertsService {
    void createExpertFromMember(CreateBasicMember command, Member member, Optional<OrgDTO> memberOrg);
}
