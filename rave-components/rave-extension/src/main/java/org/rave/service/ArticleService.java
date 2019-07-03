package org.rave.service;

import java.util.List;

import org.rave.model.Article;


public interface ArticleService {

	public void addArticle(Article article);

	public List<Article> listArticles();
}