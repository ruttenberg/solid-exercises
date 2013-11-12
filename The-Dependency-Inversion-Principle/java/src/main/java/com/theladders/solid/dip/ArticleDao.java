package com.theladders.solid.dip;

import java.util.List;

public interface ArticleDao
{
  public void updateByPrimaryKeySelective(@SuppressWarnings("unused") Article article);

  public ArticleID insertReturnId(@SuppressWarnings("unused") Article article);

  public List<? extends Article> selectByExampleWithBlobs(@SuppressWarnings("unused") Datum criteria);
}
