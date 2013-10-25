package com.theladders.solid.ocp.resume;

/**
 * Created with IntelliJ IDEA.
 * User: jpr
 * Date: 10/25/13
 * Time: 11:32 AM
 * To change this template use File | Settings | File Templates.
 */
abstract public class ConfidentialPhraseCategoryBase
{
  protected String name;
  protected int ordinal;

  public String name()
  {
    return name;
  }

  public int ordinal()
  {
    return ordinal;
  }
}
