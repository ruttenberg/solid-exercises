package com.theladders.solid.srp.resume;

import java.util.HashMap;
import java.util.Map;

import com.theladders.solid.srp.ApplicationResult;
import com.theladders.solid.srp.ApplyController;
import com.theladders.solid.srp.http.HttpResponse;

/**
 * Created with IntelliJ IDEA.
 * User: jpr
 * Date: 10/24/13
 * Time: 12:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class NeedsResumeCompletionResult extends ApplicationResult
{
  public HttpResponse makeResponse(HttpResponse response)
  {
    Map<String, Object> model = new HashMap<>();
    model.put("jobId", getJobID());
    ApplyController.provideResumeCompletionView(response, model);
    return response;
  }
}
