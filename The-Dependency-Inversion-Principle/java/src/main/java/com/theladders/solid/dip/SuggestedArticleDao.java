package com.theladders.solid.dip;

import java.util.Collections;
import java.util.List;

public class SuggestedArticleDao implements ArticleDao
{
  public void updateByPrimaryKeySelective(@SuppressWarnings("unused") Article article) {}

  public ArticleID insertReturnId(@SuppressWarnings("unused") Article article)
  {
    return null;
  }

  public List<? extends Article> selectByExampleWithBlobs(@SuppressWarnings("unused") Datum criteria)
  {
    return Collections.singletonList(new SuggestedArticle());
  }
}
