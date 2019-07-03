package org.rave.dao;


import java.util.List;

import org.rave.model.Article;



public interface ArticleDao {
	// To Save the article detail
	public void saveArticle ( Article Article );
	
	// To get list of all articles
	public List<Article> listArticles();
}