package com.theladders.solid.dip;

public interface Repository
{
  public PropertyStore getNodeForArticle(ContentID aContentID);
}
