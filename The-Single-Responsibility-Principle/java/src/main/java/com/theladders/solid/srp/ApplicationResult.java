package com.theladders.solid.srp;

import com.theladders.solid.srp.http.HttpResponse;

/**
 * Created with IntelliJ IDEA.
 * User: jpr
 * Date: 10/24/13
 * Time: 11:58 AM
 * To change this template use File | Settings | File Templates.
 */
abstract public class ApplicationResult
{
  private int theJobID = 0;

  public int getJobID()
  {
    return theJobID;
  }

  public void setJobID(int aJobID)
  {
    theJobID = aJobID;
  }

  abstract public HttpResponse makeResponse(HttpResponse aHttpResponse);
}
