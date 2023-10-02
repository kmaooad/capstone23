package edu.kmaooad.capstone23.departments.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.departments.commands.RelateJobToDepartment;
import edu.kmaooad.capstone23.departments.dal.*;
import edu.kmaooad.capstone23.departments.events.JobToDepartmentRelated;
import edu.kmaooad.capstone23.jobs.dal.Job;
import edu.kmaooad.capstone23.jobs.dal.JobRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import java.util.ArrayList;


@RequestScoped
public class RelateJobToDepartmentHandler implements CommandHandler<RelateJobToDepartment, JobToDepartmentRelated> {
    @Inject
    private DepartmentsRepository departmentsRepository;

    @Inject
    private JobRepository jobRepository;

    public Result<JobToDepartmentRelated> handle(RelateJobToDepartment command) {
        String departmentId = command.getDepartmentId();

        Department department = departmentsRepository.findById(departmentId);

        if (department == null) {
            return new Result(ErrorCode.EXCEPTION, "Department not found");
        }

        String jobId = command.getJobId();

        Job job = jobRepository.findById(new ObjectId(jobId));

        if (job == null) {
            return new Result(ErrorCode.EXCEPTION, "Job not found");
        }
        if (department.jobs == null) {
            department.jobs = new ArrayList<String>();
        }

        department.jobs.add(jobId);

        departmentsRepository.update(department);

        JobToDepartmentRelated result = new JobToDepartmentRelated(departmentId, jobId);

        return new Result(result);
    }
}
