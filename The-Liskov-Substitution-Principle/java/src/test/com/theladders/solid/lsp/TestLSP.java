package com.theladders.solid.lsp;

import org.junit.*;
import static org.junit.Assert.*;


/**
 * Created with IntelliJ IDEA.
 * User: jpr
 * Date: 10/29/13
 * Time: 11:39 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestLSP
{
  @Test
  public void SecureAndLoggedIn()
  {
    String hostName = "www.theladders.com/";

    EnvSetupFilter filter = new EnvSetupFilter(hostName);

    boolean isSecure = true;
    boolean loggedInUser = true;

    DynamicEnvironment env = filter.getEnvironment(isSecure, loggedInUser);

//    System.out.println(env.get("home"));
    assertEquals( env.get("isSSL"), "true"  );
    assertEquals(env.get("secureHome"), "https://www.theladders.com/member/");
    assertEquals(env.get("secureGuestSiteHome"), "https://www.theladders.com/");
    assertEquals(env.get("secureFalconSiteHome"), "https://www.theladders.com/");
    assertEquals(env.get("secureMemberSiteHome"), "https://www.theladders.com/member/");
    assertEquals(env.get("secureHome"), "https://www.theladders.com/member/");
    assertEquals(env.get("memberSiteHome"), "http://www.theladders.com/member/");
    assertEquals(env.get("home"), "http://www.theladders.com/member/");
    assertEquals(env.get("guestSiteHome"), "http://www.theladders.com/");
    assertEquals(env.get("falconSiteHome"), "http://www.theladders.com/");
  }

  @Test
  public void SecureAndNotLoggedIn()
  {
    String hostName = "www.theladders.com/";

    EnvSetupFilter filter = new EnvSetupFilter(hostName);

    boolean isSecure = true;
    boolean loggedInUser = false;

    DynamicEnvironment env = filter.getEnvironment(isSecure, loggedInUser);

//   System.out.println(env);

    assertEquals( env.get("isSSL"), "true"  );
    assertEquals(env.get("home"), "http://www.theladders.com/");
    assertEquals(env.get("secureHome"), "https://www.theladders.com/");
    assertEquals(env.get("secureGuestSiteHome"), "https://www.theladders.com/");
    assertEquals(env.get("secureFalconSiteHome"), "https://www.theladders.com/");
    assertEquals(env.get("secureMemberSiteHome"), "https://www.theladders.com/member/");
    assertEquals(env.get("memberSiteHome"), "http://www.theladders.com/member/");
    assertEquals(env.get("guestSiteHome"), "http://www.theladders.com/");
    assertEquals(env.get("falconSiteHome"), "http://www.theladders.com/");

  }

  @Test
  public void NotSecureAndNotLoggedIn()
  {
    String hostName = "www.theladders.com/";

    EnvSetupFilter filter = new EnvSetupFilter(hostName);

    boolean isSecure = false;
    boolean loggedInUser = false;

    DynamicEnvironment env = filter.getEnvironment(isSecure, loggedInUser);

//    System.out.println(env);

    assertEquals( env.get("isSSL"), "true"  );
    assertEquals(env.get("home"), "http://www.theladders.com/");
    assertEquals(env.get("secureHome"), "https://www.theladders.com/");
    assertEquals(env.get("secureGuestSiteHome"), "https://www.theladders.com/");
    assertEquals(env.get("secureFalconSiteHome"), "https://www.theladders.com/");
    assertEquals(env.get("secureMemberSiteHome"), "https://www.theladders.com/member/");
    assertEquals(env.get("memberSiteHome"), "http://www.theladders.com/member/");
    assertEquals(env.get("guestSiteHome"), "http://www.theladders.com/");
    assertEquals(env.get("falconSiteHome"), "http://www.theladders.com/");
  }

  @Test
  public void NotSecureAndLoggedIn()
  {
    String hostName = "www.theladders.com/";

    EnvSetupFilter filter = new EnvSetupFilter(hostName);

    boolean isSecure = false;
    boolean loggedInUser = true;

    DynamicEnvironment env = filter.getEnvironment(isSecure, loggedInUser);

// System.out.println(env);

    assertEquals( env.get("isSSL"), "true"  );
    assertEquals(env.get("home"), "http://www.theladders.com/member/");
    assertEquals(env.get("secureHome"), "https://www.theladders.com/member/");
    assertEquals(env.get("secureGuestSiteHome"), "https://www.theladders.com/");
    assertEquals(env.get("secureFalconSiteHome"), "https://www.theladders.com/");
    assertEquals(env.get("secureMemberSiteHome"), "https://www.theladders.com/member/");
    assertEquals(env.get("memberSiteHome"), "http://www.theladders.com/member/");
    assertEquals(env.get("guestSiteHome"), "http://www.theladders.com/");
    assertEquals(env.get("falconSiteHome"), "http://www.theladders.com/");
  }
}

