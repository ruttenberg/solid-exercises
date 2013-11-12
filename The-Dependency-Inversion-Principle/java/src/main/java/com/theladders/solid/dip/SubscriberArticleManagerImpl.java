package com.theladders.solid.dip;

import java.util.Date;
import java.util.List;

public class SubscriberArticleManagerImpl implements SubscriberArticleManager
{

  private ArticleDao articleDao;
  private Repository repository;
  private CDNHelper theCDNHelper;


  public SubscriberArticleManagerImpl(ArticleDao articleDao,
                                      Repository repository, CDNHelper aCDNHelper)
  {
    this.articleDao = articleDao;
    this.repository = repository;
    this.theCDNHelper = aCDNHelper;
  }


  public List<? extends Article> getArticlesbySubscriber(Subscriber aSubscriber,
                                               BasicCriteria basicCriteria)
  {
    basicCriteria.createExtendedCriteria()
            .forSubscriber(aSubscriber)
            .newOrViewed()  // must be New or Viewed
            .fromHTPConsultant();

    basicCriteria.mostRecentlyRecommendedComeFirst();

    List<? extends Article> dbSuggestions = this.articleDao.findArticleByExample(basicCriteria);

    // Fetch content associated with Article (based on externalArticleId)
    resolveArticles(dbSuggestions);

    return dbSuggestions;
  }


  public ArticleID addSuggestedArticle(Article article)
  {
//    Integer STATUS_UNREAD = 1;
//    Integer HTP_CONSULTANT = 1;

//    article.setSuggestedArticleStatusId(STATUS_UNREAD);
    article.makeStatusUnread();

//    article.setSuggestedArticleSourceId(HTP_CONSULTANT);
    article.makeSourceHTPConsultant();

    article.setCreateTime(new Date()); // current date
    article.setUpdateTime(new Date()); // current date
    ArticleID newId = articleDao.insertReturnId(article);
    return newId;
  }

  private void resolveArticles(List<? extends Article> dbArticles)
  {
    for (Article article : dbArticles)
      extractContent(article); // Attempt to fetch the actual content;
  }


  private void extractContent(Article article)
  {
    PropertyStore content = this.repository.getNodeForArticle(article.getArticleExternalIdentifier());
    if (content != null && ContentUtils.isPublishedAndEnabled(content))
    {
      // Override miniImagePath
      theCDNHelper.overrideMiniImagePath(content);
      article.setContent(content);
    }
  }


  public void updateNote(String note, Article article)
  {
    article.setNote(note);
    articleDao.updateArticle(article);
  }

  public void markRecomDeleted(Article article)
  {
//    Integer STATUS_DELETED = 4;
//    article.setSuggestedArticleStatusId(STATUS_DELETED);
    article.makeDeleted();
    articleDao.updateArticle(article);
  }
}