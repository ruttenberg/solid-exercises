package com.theladders.solid.srp;

import com.theladders.solid.srp.http.HttpResponse;

public interface HttpResponseMaker
{
//  public int getJobID();
  public HttpResponse makeResponse(HttpResponse aHttpResponse);
}
