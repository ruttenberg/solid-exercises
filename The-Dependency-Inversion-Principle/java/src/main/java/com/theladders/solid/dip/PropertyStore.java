package com.theladders.solid.dip;

public interface PropertyStore
{
  public Object getProperty(String key);

  public void addProperty(String key, Object value);
}
