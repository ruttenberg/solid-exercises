package com.theladders.solid.dip;

import java.util.Collections;
import java.util.List;

public class SuggestedArticleDao implements ArticleDao
{
  public void updateArticle(@SuppressWarnings ("unused") Article article) {}

  public ArticleID insertReturnId(@SuppressWarnings("unused") Article article)
  {
    return null;
  }

  public List<? extends Article> findArticleByExample(@SuppressWarnings ("unused") BasicCriteria criteria)
  {
    return Collections.singletonList(new SuggestedArticle());
  }
}
