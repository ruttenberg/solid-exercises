package com.theladders.solid.dip;

public interface Repository
{
  public PropertyStore getNodeByUuid(String id);
}
