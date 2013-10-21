package com.theladders.solid.srp;

import com.theladders.solid.srp.resume.MyResumeManager;
import com.theladders.solid.srp.resume.Resume;
import com.theladders.solid.srp.jobseeker.Jobseeker;
import com.theladders.solid.srp.resume.ResumeManager;

/**
 * Created with IntelliJ IDEA.
 * User: jpr
 * Date: 10/21/13
 * Time: 2:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class ResumePolicy
{
  private MyResumeManager theMyResumeManager;

  public ResumePolicy(MyResumeManager aMyResumeManager)
  {
    theMyResumeManager = aMyResumeManager;
  }

  public Resume saveNewOrRetrieveExistingResume(String newResumeFileName,
                                                 Jobseeker jobseeker, boolean useExistingResume,
                                                 boolean makeResumeActive, ResumeManager resumeManager)
  {
    Resume resume;

    if (useExistingResume)
      return getActiveResume(jobseeker.getId());

    resume = ResumePolicy.saveResume(jobseeker, newResumeFileName, resumeManager);

    if (resume == null)
      return resume;

    if (makeResumeActive)
      saveAsActive(jobseeker, resume );

    return resume;
  }

  private Resume getActiveResume(int jobseekerId)
  {
    return theMyResumeManager.getActiveResume(jobseekerId);
  }

  private void saveAsActive(Jobseeker jobseeker,
                           Resume resume)
  {
    theMyResumeManager.saveAsActive(jobseeker, resume);
  }

  private static Resume saveResume(Jobseeker jobseeker,
                           String fileName, ResumeManager resumeManager)
  {
    return resumeManager.saveResume(jobseeker, fileName);
  }

}
