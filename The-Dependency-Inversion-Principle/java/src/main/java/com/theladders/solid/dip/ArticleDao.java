package com.theladders.solid.dip;

import java.util.List;

public interface ArticleDao
{
  public void updateArticle(@SuppressWarnings ("unused") Article article);

  public ArticleID insertReturnId(@SuppressWarnings("unused") Article article);

  public List<? extends Article> findArticleByExample(@SuppressWarnings ("unused") BasicCriteria criteria);
}
