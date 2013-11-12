package com.theladders.solid.dip;

import java.util.List;

public interface Criteria
{
  public void addCriterion(String condition,
                              Object value,
                              String property);

  public void addCriterion(String condition,
                              List<? extends Object> values,
                              String property);

//  public Criteria andSubscriberIdEqualTo(Subscriber aSubscriber);
  public Criteria forSubscriber(Subscriber aSubscriber);

  public Criteria fromHTPConsultant();

  public Criteria newOrViewed();

//  public Criteria mostRecentlyRecommendedComeFirst();
}
