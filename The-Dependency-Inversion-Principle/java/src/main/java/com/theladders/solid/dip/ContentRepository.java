package com.theladders.solid.dip;

public class ContentRepository implements Repository
{
  public PropertyStore getNodeByUuid(String id)
  {
    PropertyStore node = new ContentNode();

    node.addProperty("uuid", id);

    return node;
  }
}
