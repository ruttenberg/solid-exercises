package com.theladders.solid.ocp;

import org.junit.*;

import com.theladders.solid.ocp.resume.ConfidentialPhraseCompanyName;
import com.theladders.solid.ocp.resume.ConfidentialPhraseContactInfo;
import com.theladders.solid.ocp.resume.ConfidentialPhraseEmailAddress;
import com.theladders.solid.ocp.resume.ConfidentialPhraseMailingAddress;
import com.theladders.solid.ocp.resume.ConfidentialPhraseName;
import com.theladders.solid.ocp.resume.ConfidentialPhrasePhoneNumber;
import com.theladders.solid.ocp.resume.ConfidentialPhraseTwitterHandle;
import com.theladders.solid.ocp.resume.ConfidentialPhraseWorkExperience;

import static org.junit.Assert.*;


/**
 * Created with IntelliJ IDEA.
 * User: jpr
 * Date: 10/25/13
 * Time: 2:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestOCP
{
  @Test
  public void canCreateConfidentialPhraseName()
  {
    ConfidentialPhraseName aConfidentialPhraseName  = new ConfidentialPhraseName();

    assertEquals(76, aConfidentialPhraseName.ordinal());
    assertEquals("Name", aConfidentialPhraseName.name());
  }

  @Test
  public void canCreateConfidentialPhraseMailingAddress()
  {
    ConfidentialPhraseMailingAddress aConfidentialPhraseMailingAddress  = new ConfidentialPhraseMailingAddress();

    assertEquals(79, aConfidentialPhraseMailingAddress.ordinal());
    assertEquals("MailingAddress", aConfidentialPhraseMailingAddress.name());
  }

  @Test
  public void canCreateConfidentialPhrasePhoneNumber()
  {
    ConfidentialPhrasePhoneNumber aConfidentialPhrasePhoneNumber  = new ConfidentialPhrasePhoneNumber();

    assertEquals(78, aConfidentialPhrasePhoneNumber.ordinal());
    assertEquals("PhoneNumber", aConfidentialPhrasePhoneNumber.name());
  }

  @Test
  public void canCreateConfidentialPhraseEmailAddress()
  {
    ConfidentialPhraseEmailAddress aConfidentialPhraseEmailAddress  = new ConfidentialPhraseEmailAddress();

    assertEquals(77, aConfidentialPhraseEmailAddress.ordinal());
    assertEquals("EmailAddress", aConfidentialPhraseEmailAddress.name());
  }

  @Test
  public void canCreateConfidentialPhraseContactInfo()
  {
    ConfidentialPhraseContactInfo aConfidentialPhraseContactInfo  = new ConfidentialPhraseContactInfo();

    assertEquals(80, aConfidentialPhraseContactInfo.ordinal());
    assertEquals("ContactInfo", aConfidentialPhraseContactInfo.name());
  }

  @Test
  public void canCreateConfidentialPhraseCompanyName()
  {
    ConfidentialPhraseCompanyName aConfidentialPhraseCompanyName  = new ConfidentialPhraseCompanyName();

    assertEquals(81, aConfidentialPhraseCompanyName.ordinal());
    assertEquals("CompanyName", aConfidentialPhraseCompanyName.name());
  }

  @Test
  public void canCreateConfidentialPhraseWorkExperience()
  {
    ConfidentialPhraseWorkExperience aConfidentialPhraseWorkExperience  = new ConfidentialPhraseWorkExperience();

    assertEquals(82, aConfidentialPhraseWorkExperience.ordinal());
    assertEquals("WorkExperience", aConfidentialPhraseWorkExperience.name());
  }

  @Test
  public void canCreateConfidentialPhraseTwitterHandle()
  {
    ConfidentialPhraseTwitterHandle aConfidentialPhraseTwitterHandle  = new ConfidentialPhraseTwitterHandle();

    assertEquals(83, aConfidentialPhraseTwitterHandle.ordinal());
    assertEquals("TwitterHandle", aConfidentialPhraseTwitterHandle.name());
  }
}
