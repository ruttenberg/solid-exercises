package com.theladders.solid.srp;

import java.util.HashMap;
import java.util.Map;

import com.theladders.solid.srp.http.HttpResponse;

/**
 * Created with IntelliJ IDEA.
 * User: jpr
 * Date: 10/24/13
 * Time: 12:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class SuccessResponseMaker extends SuccessResult implements HttpResponseMaker
{
  public HttpResponse makeResponse(HttpResponse response)
  {
    Map<String, Object> model = new HashMap<>();
    model.put("jobId", getJobID());
    ApplyController.provideApplySuccessView(response, model);
    return response;
  }
}
