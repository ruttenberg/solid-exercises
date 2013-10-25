package com.theladders.solid.ocp.resume;

import java.util.LinkedList;
import java.util.List;

import com.theladders.solid.ocp.user.User;

public class ResumeConfidentialityManager
{
  private final ConfidentialResumeHandler confidentialResumeHandler;
  private List<ConfidentialPhraseCategory> contactCategories;
  private List<ConfidentialPhraseCategory> allCategories;


  public ResumeConfidentialityManager(ConfidentialResumeHandler confidentialResumeHandler)
  {
    this.confidentialResumeHandler = confidentialResumeHandler;

    contactCategories = new LinkedList<ConfidentialPhraseCategory>();
    contactCategories.add(ConfidentialPhraseCategory.PhoneNumber);
    contactCategories.add(ConfidentialPhraseCategory.EmailAddress);
    contactCategories.add(ConfidentialPhraseCategory.MailingAddress);
    contactCategories.add(ConfidentialPhraseCategory.ContactInfo);

    allCategories = new LinkedList<ConfidentialPhraseCategory>();
    allCategories.add(ConfidentialPhraseCategory.Name);
    allCategories.add(ConfidentialPhraseCategory.PhoneNumber);
    allCategories.add(ConfidentialPhraseCategory.EmailAddress);
    allCategories.add(ConfidentialPhraseCategory.MailingAddress);
    allCategories.add(ConfidentialPhraseCategory.ContactInfo);
    allCategories.add(ConfidentialPhraseCategory.CompanyName);
    allCategories.add(ConfidentialPhraseCategory.WorkExperience);
  }

  public void makeAllContactInfoNonConfidential(User user)
  {
//    confidentialResumeHandler.makeAllContactInfoNonConfidential(user);
    confidentialResumeHandler.makeCategoriesNonConfidential(user, allCategories);
  }

  public void makeAllCategoriesNonConfidential(User user)
  {
//    confidentialResumeHandler.makeAllCategoriesNonConfidential(user);
    confidentialResumeHandler.makeCategoriesNonConfidential(user, contactCategories);
  }
}
