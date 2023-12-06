[![codecov](https://codecov.io/gh/kmaooad/capstone23/graph/badge.svg?token=0uAZQSSsdj)](https://codecov.io/gh/kmaooad/capstone23) [![CI](https://github.com/kmaooad/capstone23/actions/workflows/ci.yaml/badge.svg)](https://github.com/kmaooad/capstone23/actions/workflows/ci.yaml)

# Development

## Setup

- Java 17+
- Maven 3.8.6+
- MongoDB (either installed locally or with a free cluster in MongoDB Atlas)
  - Set connection string to `QUARKUS_MONGODB_CONNECTION_STRING` either as OS environment variable or in the .env file in the app folder (make sure to add `.env` file to .gitignore!)
- IDE of choice

## Running app

Run app with this command
`./mvnw compile quarkus:dev`

By default app opens at http://localhost:8080
API page (aka Swagger UI) is at http://localhost:8080/q/swagger-ui

# Week iterations (assignments)

- Week iteration starts on Tue at 08:30 Kyiv time
- Week iteration is performed by a pair of developers (2 students)
- Pairs are not fixed and can change between iterations
- Week iteration is graded for the pair as a whole
- Week iteration submission is Pull Request (PR) in GitHub. **Status checks (CI) are required to pass to PR to be considered for review and grading. CI status check requires successful build with test passing and code coverage of 90%.**

## Week iteration PR may have 3 outcomes:

- **PR is approved.** You receive as many points as complete work units included into PR (<=5)
- **PR has changes requested.** Means submitted code is generally good but has some issues and needs improvements. Once changes are made and PR is re-submitted, it can be _still graded with full points_. Changes can be submitted _during next week without loss of points_.
- **PR is closed.** Means PR does not have any meaningful code and needs to be completely re-made. Late submission of new PR leads to loss of points per late submission rules (see below).

## Submission grading

- **In-time submission.** PR that is submitted up until start of week (< Tue 8:30 Kyiv time) is considered in-time. _Any games with last-second submission may lead to late submission._ Also, remember that PRs are not monitored and reviewed 24/7, so if you expect reasonable feedback with plenty of time left for addressing possible issues, submit at least the day before. If you submit the last moment (or at night) and PR is rejected (closed), you will not have time to re-make it and this may lead to late submission.
- **Late submission.** PR submitted through next week is considered late. With positive outcome of PR review this gives 60% of points. Only one week of delay is allowed. Once PR is not approved within 2 weeks, further submission does not make any points.

## Week interation unit of work (UoW)

Required weekly code increment is 5 "units of work" _per dev pair_.

Each UoW consists of:

- Implementation of the "blackbox" test for 1 test case from one of the app features (listed below)
- Implementation of the class responsible for handling the test case
- Unit tests for submitted portion of code

# App features

- ACL
  - Check access
    - For incoming API request, check that requested command and resource are allowed for current user
  - Set/update access rules
    - Create access rule. Rule is ‘allow’ or ‘deny’ from specific user OR department OR org to specific resource OR department OR org OR module
    - Update access rule
    - Delete access rule
  - Ban/unban an org/member
    - Ban specific user OR department OR organisation from access to the whole system
    - Remove ban
- Experts
  - Invite expert by email (education/industry/both)
    - For given email and expert type generate unique invitation URL and send invitation email
    - Accept invitation, create expert
  - Manage experts
    - Create an expert in existing org
    - Update expert
    - Delete expert
  - Request to join
    - Submit a request to join platform providing info about you and your org (new or choosing existing one)
  - Approve a join request
    - Approve submitted join request, org is created, expert is created and added to org, email notification sent
    - Reject submitted join request, notify by email
  - Assign experts from existing members
    - Mark existing member an expert
    - Remove expert status from member
   
-Proffesors
  -Mass create/update/delete proffesors 
  -assign a group of students to proffesor
  -Assign activities (pending and completed)
  -assign job
  -Manually assign/unassign activity to proffesor

- Students
  - Bulk import
    - Mass create/update students from CSV, send email notifications
  - Manage group templates and groups
    - Create/update/delete student group templates (e.g. “4th grade”)
    - Create/update/delete groups (e.g. “4th grade 2022”)
  - Assign activities (pending and completed)
    - Manually assign/unassign activity to group
    - Manually assign/unassign activity to student
  - Auto-update activities based on schedule (i.e. when activity start date passes, mark it as ‘in progress’ for the group, when activity end date passes, mark it as completed by the group)
- Activities
  - Bulk import courses/course projects
    - Mass create/update courses
  - Manage extracurricular activities
    - Create/update/delete any extra activities (e.g. projects, hackathons, challenges etc.)
    - Find extracurricular activities by tags
  - Manage courses
    - Manually create/update/delete courses
  - Assign competences to activities
    - Manually set/unset relation between activity and competences
- Jobs
  - Manage jobs
    - Create/update/delete job
    - Activate/deactivate job (inactive job is visible, but cannot be applied for)
    - Relate jobs to competences
    - Relate jobs to activities
  - Manage CVs
    - Create/update/delete CV
    - Set job preferences (schedule, capacity, location, industries, competences, )
    - Hide/show CV
    - Set status (‘open for hires’)
    - Set preference ‘add competences automatically’
    - Manually add/remove competences
    - If set as preference, automatically add competences to CV based on completed activities
  - Manage hiring status for org/dep
    - Set org/dept as ‘We are hiring’
    - Stop hiring and deactivate all jobs
  - Propose a job
  - Apply for a job
- Search & reports
  Note: This service requires its own data views and keeping them up-to-date with other services
  - Find students by competences
    - Query students filtered by own fields (e.g. group, org, dept) and competences
  - Get competences summary for student group
    - For given student group, return all competences gained by its students, aggregated by count
  - Find activities by competences
    - Query activities filtered by own fields (e.g. group, org, dept) and competences
  - Save search criteria and receive email updates when new matching items found
    - For any search query, save it for watching and notification on changes
- Competences
  - Manage knowledge areas (topics)
    - Create/update/delete topics (name, parent topic)
  - Manage skills
    - Create/update/delete skills (name, parent skill)
  - Manage skill sets
    - Create/update/delete skill sets
    - Add/remove skills to/from sets
  - Manage projects
    - Create/update/delete skill-based projects (title, description, topics, skills, skill sets)
- Organizations
  - Manage orgs
    - Create/update/delete org as admin (type: education/industry, name, website, description)
    - Add/delete org logo
  - Self-signup
    - Submit org as org member
  - Activate/deactivate an org
    - Approve org submission and notify by email
    - Reject org submission with reason/suggestions
  - Manage departments
    - Create/update/delete depts (name, desc, parent)
    - Add/delete department logo
  - Manage members
  - Assign/change a member’s role (admin/member)
  - Join an org with corp email
  - Request to join an org/dep
  - Approve a join request
  - Bulk import
- Tags
  - Manage tags
    - Create/update/delete tags(name)
  - Add tags to activities(course and extracurricular)
