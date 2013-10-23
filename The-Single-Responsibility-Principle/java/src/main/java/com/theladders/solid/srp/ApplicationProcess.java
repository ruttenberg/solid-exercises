package com.theladders.solid.srp;

import com.theladders.solid.srp.job.Job;
import com.theladders.solid.srp.job.JobSearchService;
import com.theladders.solid.srp.job.application.JobApplicationResult;
import com.theladders.solid.srp.job.application.JobApplicationSystem;
import com.theladders.solid.srp.job.application.UnprocessedApplication;
import com.theladders.solid.srp.jobseeker.Jobseeker;
import com.theladders.solid.srp.jobseeker.JobseekerProfileManager;
import com.theladders.solid.srp.resume.MyResumeManager;
import com.theladders.solid.srp.resume.Resume;
import com.theladders.solid.srp.resume.ResumeManager;

/**
 * Created with IntelliJ IDEA.
 * User: jpr
 * Date: 10/18/13
 * Time: 1:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class ApplicationProcess
{

  private JobApplicationSystem    jobApplicationSystem;
  private ResumePolicy            theResumePolicy;
  private JobSearchService        theJobSearchService;
  private JobseekerProfileManager theJobseekerProfileManager;
  private Job                     theJob = null;

  public enum ApplicationStatus
  {
    SUCCESS, NO_JOB, NEEDS_RESUME_COMPLETION, ERROR
  }


  public ApplicationProcess(
                         JobApplicationSystem jobApplicationSystem,
                         ResumeManager resumeManager,
                         MyResumeManager myResumeManager,
                         JobSearchService aJobSearchService,
                         JobseekerProfileManager aJobseekerProfileManager)
  {
    this.jobApplicationSystem = jobApplicationSystem;
    this.theJobSearchService = aJobSearchService;
    this.theJobseekerProfileManager = aJobseekerProfileManager;
    this.theResumePolicy = new ResumePolicy(myResumeManager, resumeManager);
  }

  ApplicationStatus doApplication(int jobId, Jobseeker jobseeker, String fileName, boolean useExistingResume, boolean makeResumeActive)
  {
    theJob = theJobSearchService.getJob(jobId);
    if (theJob == null)
      return ApplicationStatus.NO_JOB;

    Resume resume = theResumePolicy.saveNewOrRetrieveExistingResume(fileName, jobseeker, useExistingResume, makeResumeActive);
    if (resume == null)
      return ApplicationStatus.ERROR;

    UnprocessedApplication application = new UnprocessedApplication(jobseeker, theJob, resume);
    JobApplicationResult applicationResult = jobApplicationSystem.apply(application);

    if (applicationResult.failure())
      return ApplicationStatus.ERROR;

    ProfileService aProfileService = new ProfileService(jobseeker, theJobseekerProfileManager);
    if (aProfileService.needsResumeCompletion(jobseeker.isPremium()))
      return ApplicationStatus.NEEDS_RESUME_COMPLETION;

    return ApplicationStatus.SUCCESS;
  }

  public int getJobId()
  {
    return theJob.getJobId();
  }
}
