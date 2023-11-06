package edu.kmaooad.capstone23.departments.handlers;

import edu.kmaooad.capstone23.ban.dal.BannedEntityType;
import edu.kmaooad.capstone23.ban.service.EntityBanService;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.departments.commands.RelateJobToDepartment;
import edu.kmaooad.capstone23.departments.dal.*;
import edu.kmaooad.capstone23.departments.events.JobToDepartmentRelated;
import edu.kmaooad.capstone23.departments.services.DepartmentService;
import edu.kmaooad.capstone23.jobs.service.JobService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.ArrayList;


@RequestScoped
public class RelateJobToDepartmentHandler implements CommandHandler<RelateJobToDepartment, JobToDepartmentRelated> {
    @Inject
    private DepartmentService departmentsService;

    @Inject
    private JobService jobService;

    @Inject
    private EntityBanService banService;

    public Result<JobToDepartmentRelated> handle(RelateJobToDepartment command) {
        String departmentId = command.getDepartmentId();

        Department department = departmentsService.getDepartmentById(departmentId);

        if (department == null) {
            return new Result<>(ErrorCode.EXCEPTION, "Department not found");
        }
        if (banService.findForEntity(BannedEntityType.Department, department.id).isPresent()) {
            return new Result<>(ErrorCode.EXCEPTION, "Department is banned");
        }

        String jobId = command.getJobId();

        var maybeJob = jobService.findJobById(jobId);

        if (maybeJob.isEmpty()) {
            return new Result<>(ErrorCode.EXCEPTION, "Job not found");
        }
        if (department.jobs == null) {
            department.jobs = new ArrayList<>();
        }

        department.jobs.add(jobId);

        departmentsService.updateDepartment(department);

        JobToDepartmentRelated result = new JobToDepartmentRelated(departmentId, jobId);

        return new Result<>(result);
    }
}
