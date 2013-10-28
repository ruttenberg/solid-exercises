package com.theladders.solid.srp;

import com.theladders.solid.srp.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: jpr
 * Date: 10/24/13
 * Time: 12:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class ErrorResult extends ApplicationResult
{
  public HttpResponse makeResponse(HttpResponse response)
  {
    List<String> errList = new ArrayList<>();
    Map<String, Object> model = new HashMap<>();

    errList.add("We could not process your application.");
    ApplyController.provideErrorView(response, errList, model);
    return response;
  }
}
