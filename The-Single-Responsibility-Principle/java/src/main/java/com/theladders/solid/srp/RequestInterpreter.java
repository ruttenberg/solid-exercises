package com.theladders.solid.srp;

import com.theladders.solid.srp.http.HttpRequest;
import com.theladders.solid.srp.jobseeker.Jobseeker;

/**
 * Created with IntelliJ IDEA.
 * User: jpr
 * Date: 10/21/13
 * Time: 9:55 AM
 * To change this template use File | Settings | File Templates.
 */
public class RequestInterpreter {
  static public boolean useExistingResume(HttpRequest request)
  {
    return "existing".equals(request.getParameter("whichResume"));
  }

  static public boolean makeResumeActive(HttpRequest request)
  {
    return "yes".equals(request.getParameter("makeResumeActive"));
  }

  static public Jobseeker getJobseeker(HttpRequest request)
  {
    return request.getSession().getJobseeker();
  }

  static public int getJobId(HttpRequest request)
  {
    return Integer.parseInt(request.getParameter("jobId"));
  }
}
