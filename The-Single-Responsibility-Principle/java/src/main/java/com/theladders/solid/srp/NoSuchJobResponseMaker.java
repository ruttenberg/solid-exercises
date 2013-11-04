package com.theladders.solid.srp;

import com.theladders.solid.srp.http.HttpResponse;

/**
 * Created with IntelliJ IDEA.
 * User: jpr
 * Date: 10/24/13
 * Time: 12:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class NoSuchJobResponseMaker extends NoSuchJobResult implements HttpResponseMaker
{
  public HttpResponse makeResponse(HttpResponse response)
  {
    ApplyController.provideInvalidJobView(response, getJobID());
    return response;
  }
}
