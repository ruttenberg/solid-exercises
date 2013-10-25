package com.theladders.solid.ocp.resume;

import java.util.List;

import com.theladders.solid.ocp.jobseeker.JobseekerConfidentialityProfile;
import com.theladders.solid.ocp.jobseeker.JobseekerConfidentialityProfileDao;
import com.theladders.solid.ocp.user.User;

public class ConfidentialResumeHandler
{
  static ConfidentialPhraseName           theConfidentialPhraseName           = new ConfidentialPhraseName();
  static ConfidentialPhrasePhoneNumber    theConfidentialPhrasePhoneNumber    = new ConfidentialPhrasePhoneNumber();
  static ConfidentialPhraseEmailAddress   theConfidentialPhraseEmailAddress   = new ConfidentialPhraseEmailAddress();
  static ConfidentialPhraseMailingAddress theConfidentialPhraseMailingAddress = new ConfidentialPhraseMailingAddress();
  static ConfidentialPhraseContactInfo    theConfidentialPhraseContactInfo    = new ConfidentialPhraseContactInfo();
  static ConfidentialPhraseCompanyName    theConfidentialPhraseCompanyName    = new ConfidentialPhraseCompanyName();
  static ConfidentialPhraseWorkExperience    theConfidentialPhraseWorkExperience    = new ConfidentialPhraseWorkExperience();
  static ConfidentialPhraseTwitterHandle    theConfidentialPhraseTwitterHandle    = new ConfidentialPhraseTwitterHandle();

  private final JobseekerProfileManager            jobSeekerProfileManager;
  private final JobseekerConfidentialityProfileDao jobseekerConfidentialityProfileDao;


  public ConfidentialResumeHandler(JobseekerProfileManager jobseekerProfileManager,
                                   JobseekerConfidentialityProfileDao jobseekerConfidentialityProfileDao)
  {
    this.jobSeekerProfileManager = jobseekerProfileManager;
    this.jobseekerConfidentialityProfileDao = jobseekerConfidentialityProfileDao;
  }

//  public void makeCategoriesNonConfidential(User user, List<ConfidentialPhraseCategory> someConfidentialPhraseCategories)
  public void makeCategoriesNonConfidential(User user, List<ConfidentialPhraseCategoryBase> someConfidentialPhraseCategories)
  {
    JobseekerProfile jsp = jobSeekerProfileManager.getJobSeekerProfile(user);
    JobseekerConfidentialityProfile profile = jobseekerConfidentialityProfileDao.fetchJobSeekerConfidentialityProfile(jsp.getId());

    boolean isChanged = false;

    for (ConfidentialPhraseCategoryBase category : someConfidentialPhraseCategories)
    {
      isChanged = profile.resetConfidentialFlagsForCategory(category) || isChanged;
    }

    if (isChanged)
    {
      generatePermanentConfidentialFiles(user, profile);
    }
  }

  public void makeAllCategoriesNonConfidential(User user)
  {
    JobseekerProfile jsp = jobSeekerProfileManager.getJobSeekerProfile(user);
    JobseekerConfidentialityProfile profile = jobseekerConfidentialityProfileDao.fetchJobSeekerConfidentialityProfile(jsp.getId());

    boolean isChanged = false;
    isChanged = profile.resetConfidentialFlagsForCategory(theConfidentialPhraseName) || isChanged;
    isChanged = profile.resetConfidentialFlagsForCategory(theConfidentialPhrasePhoneNumber) || isChanged;
    isChanged = profile.resetConfidentialFlagsForCategory(theConfidentialPhraseEmailAddress) || isChanged;
    isChanged = profile.resetConfidentialFlagsForCategory(theConfidentialPhraseMailingAddress) || isChanged;
    isChanged = profile.resetConfidentialFlagsForCategory(theConfidentialPhraseContactInfo) || isChanged;
    isChanged = profile.resetConfidentialFlagsForCategory(theConfidentialPhraseCompanyName) || isChanged;
    isChanged = profile.resetConfidentialFlagsForCategory(theConfidentialPhraseWorkExperience) || isChanged;

    isChanged = profile.resetConfidentialFlagsForCategory(theConfidentialPhraseTwitterHandle) || isChanged;

    if (isChanged)
    {
      generatePermanentConfidentialFiles(user, profile);
    }
  }

  public void makeAllContactInfoNonConfidential(User user)
  {
    JobseekerProfile jsp = jobSeekerProfileManager.getJobSeekerProfile(user);
    JobseekerConfidentialityProfile profile = jobseekerConfidentialityProfileDao.fetchJobSeekerConfidentialityProfile(jsp.getId());
    boolean isChanged = false;
    isChanged = profile.resetConfidentialFlagsForCategory(theConfidentialPhrasePhoneNumber) || isChanged;
    isChanged = profile.resetConfidentialFlagsForCategory(theConfidentialPhraseEmailAddress) || isChanged;
    isChanged = profile.resetConfidentialFlagsForCategory(theConfidentialPhraseMailingAddress) || isChanged;
    isChanged = profile.resetConfidentialFlagsForCategory(theConfidentialPhraseContactInfo) || isChanged;

    if (isChanged)
    {
      generatePermanentConfidentialFiles(user, profile);
    }
  }

  @SuppressWarnings("unused")
  private void generatePermanentConfidentialFiles(User user,
                                                  JobseekerConfidentialityProfile profile)
  {
    // stub
  }
}
