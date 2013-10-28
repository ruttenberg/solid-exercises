package com.theladders.solid.srp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.theladders.solid.srp.http.HttpResponse;

/**
 * Created with IntelliJ IDEA.
 * User: jpr
 * Date: 10/24/13
 * Time: 12:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class NoSuchJobResult extends ApplicationResult
{
  public HttpResponse makeResponse(HttpResponse response)
  {
    ApplyController.provideInvalidJobView(response, getJobID());
    return response;
  }
}
