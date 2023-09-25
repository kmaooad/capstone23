package edu.kmaooad.capstone23.departments.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.departments.commands.SetMemberRole;
import edu.kmaooad.capstone23.departments.events.MemberRoleSetted;
import jakarta.ws.rs.Path;

@Path("/departments/set-member-role")
public class SetMemberRoleController extends TypicalController<SetMemberRole, MemberRoleSetted> {
}