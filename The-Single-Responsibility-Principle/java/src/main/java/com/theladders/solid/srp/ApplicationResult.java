package com.theladders.solid.srp;

import com.theladders.solid.srp.http.HttpResponse;

/**
 * Created with IntelliJ IDEA.
 * User: jpr
 * Date: 10/24/13
 * Time: 11:58 AM
 * To change this template use File | Settings | File Templates.
 */
public interface ApplicationResult
{
  public int getJobID();
  public void setJobID(int aJobID);
  public HttpResponse makeResponse(HttpResponse aHttpResponse);
}
