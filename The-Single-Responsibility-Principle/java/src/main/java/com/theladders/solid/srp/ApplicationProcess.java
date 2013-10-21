package com.theladders.solid.srp;

import com.theladders.solid.srp.job.Job;
import com.theladders.solid.srp.job.application.ApplicationFailureException;
import com.theladders.solid.srp.job.application.JobApplicationResult;
import com.theladders.solid.srp.job.application.JobApplicationSystem;
import com.theladders.solid.srp.job.application.UnprocessedApplication;
import com.theladders.solid.srp.jobseeker.Jobseeker;
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
public class ApplicationProcess {

  private final JobApplicationSystem    jobApplicationSystem;
  private final ResumePolicy            theResumePolicy;

  public ApplicationProcess(
                         JobApplicationSystem jobApplicationSystem,
                         ResumeManager resumeManager,
                         MyResumeManager myResumeManager)
  {
    this.jobApplicationSystem = jobApplicationSystem;
    this.theResumePolicy = new ResumePolicy(myResumeManager, resumeManager);
  }

  /*private JobApplicationResult apply(UnprocessedApplication application)
  {
    return jobApplicationSystem.apply(application);
  }*/

  public void apply(Jobseeker jobseeker, Job job,
                     String fileName, boolean useExistingResume, boolean makeResumeActive)
  {
    Resume resume = theResumePolicy.saveNewOrRetrieveExistingResume(fileName, jobseeker, useExistingResume, makeResumeActive);
    UnprocessedApplication application = new UnprocessedApplication(jobseeker, job, resume);
    JobApplicationResult applicationResult = jobApplicationSystem.apply(application);

    if (applicationResult.failure())
      throw new ApplicationFailureException(applicationResult.toString());
  }
}
