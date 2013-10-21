package com.theladders.solid.srp;

import com.theladders.solid.srp.jobseeker.Jobseeker;
import com.theladders.solid.srp.jobseeker.JobseekerProfileManager;

/**
 * Created with IntelliJ IDEA.
 * User: jpr
 * Date: 10/21/13
 * Time: 3:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class ProfileService
{
  private Jobseeker theJobseeker;
  private JobseekerProfileManager theJobseekerProfileManager;

  ProfileService(Jobseeker aJobseeker, JobseekerProfileManager aJobseekerProfileManager)
  {
    theJobseeker = aJobseeker;
    theJobseekerProfileManager = aJobseekerProfileManager;
  }

  public boolean needsResumeCompletion(boolean isPremium)
  {
    return theJobseekerProfileManager.getJobSeekerProfile(theJobseeker).needsResumeCompletion(isPremium);

  }
}

