package com.theladders.solid.srp;

import com.theladders.solid.srp.job.Job;
import com.theladders.solid.srp.job.JobSearchService;
import com.theladders.solid.srp.job.application.JobApplicationResult;
import com.theladders.solid.srp.job.application.JobApplicationSystem;
import com.theladders.solid.srp.job.application.UnprocessedApplication;
import com.theladders.solid.srp.jobseeker.Jobseeker;
import com.theladders.solid.srp.jobseeker.JobseekerProfileManager;
import com.theladders.solid.srp.resume.MyResumeManager;
import com.theladders.solid.srp.resume.NeedsResumeCompletionResult;
import com.theladders.solid.srp.resume.Resume;
import com.theladders.solid.srp.resume.ResumeManager;
import com.theladders.solid.srp.resume.SuccessResult;

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

  ApplicationResult doApplication(int jobId,
                                  Jobseeker jobseeker,
                                  String fileName,
                                  boolean useExistingResume,
                                  boolean makeResumeActive,
                                  NoSuchJobResult aNoSuchJobResult,
                                  ErrorResult anErrorResult,
                                  NeedsResumeCompletionResult aNeedsResumeCompletionResult,
                                  SuccessResult aSuccessResult)
  {
    theJob = theJobSearchService.getJob(jobId);
    if (theJob == null)
    {
      aNoSuchJobResult.setJobID(0);
      return aNoSuchJobResult;
    }

    int foundJobID = theJob.getJobId();

    Resume resume = theResumePolicy.saveNewOrRetrieveExistingResume(fileName, jobseeker, useExistingResume, makeResumeActive);
    if (resume == null)
    {
      anErrorResult.setJobID(foundJobID);
      return anErrorResult;
    }

    UnprocessedApplication application = new UnprocessedApplication(jobseeker, theJob, resume);
    JobApplicationResult applicationResult = jobApplicationSystem.apply(application);

    if (applicationResult.failure())
    {
      anErrorResult.setJobID(foundJobID);
      return anErrorResult;
    }

    ProfileService aProfileService = new ProfileService(jobseeker, theJobseekerProfileManager);
    if (aProfileService.needsResumeCompletion(jobseeker.isPremium()))
    {
      aNeedsResumeCompletionResult.setJobID(foundJobID);
      return aNeedsResumeCompletionResult;
    }

    aSuccessResult.setJobID(foundJobID);
    return aSuccessResult;
  }
}
