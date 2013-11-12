package com.theladders.solid.dip;

public class ContentRepository implements Repository
{
  public PropertyStore getNodeForArticle(ContentID id)
  {
    PropertyStore node = new ContentNode();

    node.addProperty("uuid", id);

    return node;
  }
}
