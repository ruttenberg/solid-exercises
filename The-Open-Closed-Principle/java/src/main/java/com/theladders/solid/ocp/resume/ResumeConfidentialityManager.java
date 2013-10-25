package com.theladders.solid.ocp.resume;

import java.util.LinkedList;
import java.util.List;

import com.theladders.solid.ocp.user.User;

public class ResumeConfidentialityManager
{
  private final ConfidentialResumeHandler confidentialResumeHandler;
//  private List<ConfidentialPhraseCategory> contactCategories;
//  private List<ConfidentialPhraseCategory> allCategories;

  private List<ConfidentialPhraseCategoryBase> contactCategories;
  private List<ConfidentialPhraseCategoryBase> allCategories;


  public ResumeConfidentialityManager(ConfidentialResumeHandler confidentialResumeHandler)
  {
    this.confidentialResumeHandler = confidentialResumeHandler;

    /*
    contactCategories = new LinkedList<ConfidentialPhraseCategory>();
    contactCategories.add(ConfidentialPhraseCategory.PhoneNumber);
    contactCategories.add(ConfidentialPhraseCategory.EmailAddress);
    contactCategories.add(ConfidentialPhraseCategory.MailingAddress);
    contactCategories.add(ConfidentialPhraseCategory.ContactInfo);
    */

    contactCategories = new LinkedList<ConfidentialPhraseCategoryBase>();
    contactCategories.add(new ConfidentialPhrasePhoneNumber());
    contactCategories.add(new ConfidentialPhraseEmailAddress());
    contactCategories.add(new ConfidentialPhraseMailingAddress());
    contactCategories.add(new ConfidentialPhraseContactInfo());

    /*
    allCategories = new LinkedList<ConfidentialPhraseCategory>();
    allCategories.add(ConfidentialPhraseCategory.Name);
    allCategories.add(ConfidentialPhraseCategory.PhoneNumber);
    allCategories.add(ConfidentialPhraseCategory.EmailAddress);
    allCategories.add(ConfidentialPhraseCategory.MailingAddress);
    allCategories.add(ConfidentialPhraseCategory.ContactInfo);
    allCategories.add(ConfidentialPhraseCategory.CompanyName);
    allCategories.add(ConfidentialPhraseCategory.WorkExperience);
    */

    allCategories = new LinkedList<ConfidentialPhraseCategoryBase>();
    allCategories.add(new ConfidentialPhraseName());
    allCategories.add(new ConfidentialPhrasePhoneNumber());
    allCategories.add(new ConfidentialPhraseEmailAddress());
    allCategories.add(new ConfidentialPhraseMailingAddress());
    allCategories.add(new ConfidentialPhraseContactInfo());
    allCategories.add(new ConfidentialPhraseCompanyName());
    allCategories.add(new ConfidentialPhraseWorkExperience());
    allCategories.add(new ConfidentialPhraseTwitterHandle());
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
