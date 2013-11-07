package com.theladders.solid.dip;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubscriberArticleManagerImpl implements SubscriberArticleManager
{
  private static final String              IMAGE_PREFIX       = "http://somecdnprodiver.com/static/images/careerAdvice/";
  private static final Map<String, String> CATEGORY_IMAGE_MAP = new HashMap<>();

  static
  {
    CATEGORY_IMAGE_MAP.put("Interviewing", "interviewing_thumb.jpg");
    CATEGORY_IMAGE_MAP.put("Job Search", "job_search_thumb.jpg");
    CATEGORY_IMAGE_MAP.put("Networking", "networking_thumb.jpg");
    CATEGORY_IMAGE_MAP.put("Personal Branding", "personalBranding_thumb.jpg");
    CATEGORY_IMAGE_MAP.put("Resume", "resume_thumb.jpg");
    CATEGORY_IMAGE_MAP.put("Salary", "salary_thumb.jpg");
    CATEGORY_IMAGE_MAP.put("Assessment", "salary_thumb.jpg");
    CATEGORY_IMAGE_MAP.put("On the Job", "salary_thumb.jpg");
  }


  private ArticleDao articleDao;
  private Repository repository;


  public SubscriberArticleManagerImpl(ArticleDao articleDao,
                                      Repository repository)
  {
    this.articleDao = articleDao;
    this.repository = repository;
  }


  public List<Article> getArticlesbySubscriber(Integer subscriberId,
                                               Datum criteria)
  {
    criteria.createCriteria()
            .andSubscriberIdEqualTo(subscriberId)
            .andSuggestedArticleStatusIdIn(Arrays.asList(1, 2))  // must be New or Viewed
            .andSuggestedArticleSourceIdEqualTo(1);

    criteria.setOrderByClause("create_time desc");
    List<Article> dbSuggestions = this.articleDao.selectByExampleWithBlobs(criteria);

    // Fetch content associated with Article (based on externalArticleId)
    resolveArticles(dbSuggestions);

    return dbSuggestions;
  }


  public int addSuggestedArticle(Article article)
  {
    Integer STATUS_UNREAD = 1;
    Integer HTP_CONSULTANT = 1;
    article.setSuggestedArticleStatusId(STATUS_UNREAD);
    article.setSuggestedArticleSourceId(HTP_CONSULTANT);
    article.setCreateTime(new Date()); // current date
    article.setUpdateTime(new Date()); // current date
    int newId = articleDao.insertReturnId(article);
    return newId;
  }

  private void resolveArticles(List<Article> dbArticles)
  {
    for (Article article : dbArticles)
    {

      // Attempt to fetch the actual content;
      ContentNode content = this.repository.getNodeByUuid(article.getArticleExternalIdentifier());
      if (content != null && ContentUtils.isPublishedAndEnabled(content))
      {
        // Override miniImagePath
        overrideMiniImagePath(content);
        article.setContent(content);
      }
    }
  }

  private static void overrideMiniImagePath(ContentNode node)
  {
    String path = (String) node.getProperty("miniImagePath");

    if (path == null || path.length() == 0)
    {
      String category = (String) node.getProperty("primaryTopic");
      node.addProperty("miniImagePath", IMAGE_PREFIX + CATEGORY_IMAGE_MAP.get(category));
    }
  }

  public void updateNote(Integer id, String note, Article article)
  {
    article.setSuggestedArticleId(id);
    article.setNote(note);
    articleDao.updateByPrimaryKeySelective(article);
  }

  public void markRecomDeleted(Integer id, Article article)
  {
    Integer STATUS_DELETED = 4;
    article.setSuggestedArticleId(id);
    article.setSuggestedArticleStatusId(STATUS_DELETED);
    articleDao.updateByPrimaryKeySelective(article);
  }
}