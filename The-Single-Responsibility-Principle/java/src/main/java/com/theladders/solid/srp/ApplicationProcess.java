package com.theladders.solid.srp;

import com.theladders.solid.srp.job.Job;
import com.theladders.solid.srp.job.JobSearchService;
import com.theladders.solid.srp.job.application.ApplicationFailureException;
import com.theladders.solid.srp.job.application.JobApplicationResult;
import com.theladders.solid.srp.job.application.JobApplicationSystem;
import com.theladders.solid.srp.job.application.UnprocessedApplication;
import com.theladders.solid.srp.jobseeker.Jobseeker;
import com.theladders.solid.srp.jobseeker.JobseekerProfile;
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
public class ApplicationProcess {

  private final JobseekerProfileManager jobseekerProfileManager;
  private final JobSearchService        jobSearchService;
  private final JobApplicationSystem    jobApplicationSystem;
  private final ResumeManager           resumeManager;
  private final MyResumeManager         myResumeManager;

  public ApplicationProcess(JobseekerProfileManager jobseekerProfileManager,
                         JobSearchService jobSearchService,
                         JobApplicationSystem jobApplicationSystem,
                         ResumeManager resumeManager,
                         MyResumeManager myResumeManager)
  {
    this.jobseekerProfileManager = jobseekerProfileManager;
    this.jobSearchService = jobSearchService;
    this.jobApplicationSystem = jobApplicationSystem;
    this.resumeManager = resumeManager;
    this.myResumeManager = myResumeManager;
  }

  public JobseekerProfile getJobSeekerProfile(Jobseeker jobseeker)
  {
    return  jobseekerProfileManager.getJobSeekerProfile(jobseeker);
  }

  public Job getJob(int jobId)
  {
    return jobSearchService.getJob(jobId);
  }

  private JobApplicationResult apply(UnprocessedApplication application)
  {
    return jobApplicationSystem.apply(application);
  }

  public Resume saveResume(Jobseeker jobseeker,
                           String fileName)
  {
    return resumeManager.saveResume(jobseeker, fileName);
  }

  public void saveAsActive(Jobseeker jobseeker,
                           Resume resume)
  {
    myResumeManager.saveAsActive(jobseeker, resume);
  }

  public Resume getActiveResume(int jobseekerId)
  {
    return myResumeManager.getActiveResume(jobseekerId);
  }

  public void apply(Jobseeker jobseeker, Job job,
                     String fileName, boolean useExistingResume, boolean makeResumeActive)
  {
    Resume resume = saveNewOrRetrieveExistingResume(fileName,jobseeker, useExistingResume, makeResumeActive);
    UnprocessedApplication application = new UnprocessedApplication(jobseeker, job, resume);
    JobApplicationResult applicationResult = apply(application);

    if (applicationResult.failure())
      throw new ApplicationFailureException(applicationResult.toString());
  }

  private Resume saveNewOrRetrieveExistingResume(String newResumeFileName,
                                                 Jobseeker jobseeker, boolean useExistingResume,
                                                 boolean makeResumeActive )
  {
    Resume resume;

    if (useExistingResume)
      return getActiveResume(jobseeker.getId());

    resume = saveResume(jobseeker, newResumeFileName);

    if (resume == null)
      return resume;

    if (makeResumeActive)
      saveAsActive(jobseeker, resume);

    return resume;
  }
}
