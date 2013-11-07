package com.theladders.solid.dip;

public class ContentRepository implements Repository
{
  public ContentNode getNodeByUuid(String id)
  {
    ContentNode node = new ContentNode();

    node.addProperty("uuid", id);

    return node;
  }
}
