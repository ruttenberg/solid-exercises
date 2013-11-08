package com.theladders.solid.dip;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class SuggestedArticleDao implements ArticleDao
{
  public void updateByPrimaryKeySelective(@SuppressWarnings("unused") Article article) {}

  public int insertReturnId(@SuppressWarnings("unused") Article article)
  {
    return 0;
  }

  public List<? extends Article> selectByExampleWithBlobs(@SuppressWarnings("unused") Datum criteria)
  {

    List<Article> list = new LinkedList<Article>();
    list.add(new SuggestedArticle());
//    return list;

    return Collections.singletonList(new SuggestedArticle());
  }
}
