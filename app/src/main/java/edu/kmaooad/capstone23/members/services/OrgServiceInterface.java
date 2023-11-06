package edu.kmaooad.capstone23.members.services;

import edu.kmaooad.capstone23.members.dto.OrgDTO;
import org.bson.types.ObjectId;

import java.util.Optional;

<<<<<<<< HEAD:app/src/main/java/edu/kmaooad/capstone23/members/services/MemberOrgService.java
public interface MemberOrgService {
|||||||| 31b9851a:app/src/main/java/edu/kmaooad/capstone23/members/services/OrgService.java
public interface OrgService {
========
public interface OrgServiceInterface {
>>>>>>>> main:app/src/main/java/edu/kmaooad/capstone23/members/services/OrgServiceInterface.java
    Optional<OrgDTO> findByEmailDomainOptional(String emailDomain);

    Optional<OrgDTO> findByIdOptional(String id);
}
