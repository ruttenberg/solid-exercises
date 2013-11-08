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

  public Criteria andSubscriberIdEqualTo(Integer value);

  public Criteria andSuggestedArticleSourceIdEqualTo(Integer value);

  public Criteria andSuggestedArticleStatusIdIn(List<Integer> values);
}
