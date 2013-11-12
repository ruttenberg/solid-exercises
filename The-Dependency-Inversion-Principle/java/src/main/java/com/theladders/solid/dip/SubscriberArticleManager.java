package com.theladders.solid.dip;

import java.util.List;

public interface SubscriberArticleManager
{
  /**
   * Get a list of all active suggested articles for a given subscriber.
   *
   *   Sorted reverse chronological by time recommended
   *
   * It also gets the content of the actual articles from the CMS and stores it in
   * the Article object.
   *
   * @param aSubscriber
   *          ID of this subscriber
   * @return List of all suggested articles whose status is either New or Viewed
   */
  public List<? extends Article> getArticlesbySubscriber(Subscriber aSubscriber, Datum criteria);

  /**
   * Add a Article to the database.
   *
   * @param article
   */

  public ArticleID addSuggestedArticle(Article article);

  /**
   * Update the note of the Suggested Article(id)
   *  with the note passed in.
   *
   */
  public void updateNote(String note, Article article);

  /**
   * Mark as deleted the Suggested Article(id)
   *
   */
  public void markRecomDeleted(Article article); }