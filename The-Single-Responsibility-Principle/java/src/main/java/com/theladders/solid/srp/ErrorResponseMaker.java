package com.theladders.solid.srp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.theladders.solid.srp.http.HttpResponse;

public class ErrorResponseMaker extends ErrorResult implements HttpResponseMaker
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
