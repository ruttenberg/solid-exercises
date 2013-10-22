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
  private ResumeManager theResumeManager;

  public ResumePolicy(MyResumeManager aMyResumeManager, ResumeManager aResumeManager)
  {
    theMyResumeManager = aMyResumeManager;
    theResumeManager = aResumeManager;
  }

  public Resume saveNewOrRetrieveExistingResume(String newResumeFileName,
                                                 Jobseeker jobseeker, boolean useExistingResume,
                                                 boolean makeResumeActive)
  {
    Resume resume;

    if (useExistingResume)
      return getActiveResume(jobseeker.getId());

    if (newResumeFileName == null)
      return null;

    resume = saveResume(jobseeker, newResumeFileName);

//    if (resume == null)
//      return resume;

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

  private Resume saveResume(Jobseeker jobseeker,
                           String fileName )
  {
    return theResumeManager.saveResume(jobseeker, fileName);
  }

}
