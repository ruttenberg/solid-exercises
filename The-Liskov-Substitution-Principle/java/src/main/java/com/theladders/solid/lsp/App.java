package com.theladders.solid.lsp;

import java.util.Scanner;

public class App
{
  private static final String hostName = "www.theladders.com/";

  public static void main(String[] args)
  {
    EnvSetupFilter filter = new EnvSetupFilter(hostName);

    boolean isSecure = ask("Is the HTTP request secure?");
    boolean loggedInUser = ask("Is a user logged into the site?");

    DynamicEnvironment env = filter.getEnvironment(isSecure, loggedInUser);

    System.out.println(env);

    // true, true
    String expectSecureAndLoggedIn = "{isSSL=true, secureHome=https://www.theladders.com/member/, home=http://www.theladders.com/, secureGuestSiteHome=https://www.theladders.com/, secureFalconSiteHome=https://www.theladders.com/, secureMemberSiteHome=https://www.theladders.com/member/, secureHome=https://www.theladders.com/, memberSiteHome=http://www.theladders.com/member/, home=http://www.theladders.com/member/, guestSiteHome=http://www.theladders.com/, falconSiteHome=http://www.theladders.com/}";
    System.out.println(expectSecureAndLoggedIn);
  }

  @SuppressWarnings("resource")
  public static boolean ask(String question)
  {
    System.out.println(question + " (true/false)");

    return new Scanner(System.in).nextBoolean();
  }
}
