package com.theladders.solid.dip;

import java.util.List;

public interface ExtendedCriteria
{
  public void addCriterion(String condition,
                              Object value,
                              String property);

  public void addCriterion(String condition,
                              List<? extends Object> values,
                              String property);

//  public ExtendedCriteria andSubscriberIdEqualTo(Subscriber aSubscriber);
  public ExtendedCriteria forSubscriber(Subscriber aSubscriber);

  public ExtendedCriteria fromHTPConsultant();

  public ExtendedCriteria newOrViewed();

//  public ExtendedCriteria mostRecentlyRecommendedComeFirst();
}
