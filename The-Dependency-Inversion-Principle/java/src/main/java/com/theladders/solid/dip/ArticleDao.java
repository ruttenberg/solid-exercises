package com.theladders.solid.dip;

import java.util.List;

public interface ArticleDao
{
  public void updateByPrimaryKeySelective(@SuppressWarnings("unused") Article article);

  public int insertReturnId(@SuppressWarnings("unused") Article article);

  public List<Article> selectByExampleWithBlobs(@SuppressWarnings("unused") Datum criteria);
}
