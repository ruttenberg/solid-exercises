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

  public SuggestedArticleCriteria andSubscriberIdEqualTo(Integer value);

  public SuggestedArticleCriteria andSuggestedArticleSourceIdEqualTo(Integer value);

  public SuggestedArticleCriteria andSuggestedArticleStatusIdIn(List<Integer> values);
}
