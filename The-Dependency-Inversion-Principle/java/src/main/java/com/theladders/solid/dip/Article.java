package com.theladders.solid.dip;

import java.util.Date;

// A Article is one instance of an article that has been
// recommended to a particular subscriber.

public interface Article
{
  @Column(name = "suggested_article_id")
  public Integer getSuggestedArticleId();

  public void setSuggestedArticleId(Integer suggestedArticleId);

  @Column(name = "subscriber_id")
  public Integer getSubscriberId();

  public void setSubscriberId(Integer subscriberId);

  @Column(name = "suggested_article_source_id")
  public Integer getSuggestedArticleSourceId();

  public void setSuggestedArticleSourceId(Integer suggestedArticleSourceId);

  @Column(name = "article_external_identifier")
  public String getArticleExternalIdentifier();

  public void setArticleExternalIdentifier(String articleExternalIdentifier);

  @Column(name = "suggested_article_status_id")
  public Integer getSuggestedArticleStatusId();

  public void setSuggestedArticleStatusId(Integer suggestedArticleStatusId);

  @Column(name = "create_time")
  public Date getCreateTime();

  public void setCreateTime(Date createTime);

  @Column(name = "creator_id")
  public Integer getCreatorId();

  public void setCreatorId(Integer creatorId);

  @Column(name = "update_time")
  public Date getUpdateTime();

  public void setUpdateTime(Date updateTime);

  @Column(name = "updater_id")
  public Integer getUpdaterId();

  public void setUpdaterId(Integer updaterId);

  @Column(name = "note")
  public String getNote();

  public void setNote(String note);

  public PropertyStore getContent();

  public void setContent(PropertyStore node);

  public boolean getIsRead();

  public void setIsRead(boolean read);
}
