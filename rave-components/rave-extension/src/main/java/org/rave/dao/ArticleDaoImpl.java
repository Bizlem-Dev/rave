package org.rave.dao;

import java.util.Date;
import java.util.List;
import org.hibernate.SessionFactory;
import org.rave.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("articleDao")
public class ArticleDaoImpl implements ArticleDao {

	@Autowired
	private SessionFactory sessionFactory;

	// To Save the article detail
	public void saveArticle(Article article) {
		article.setAddedDate(new Date());
		System.out.println("Dao_save");
		sessionFactory.getCurrentSession().saveOrUpdate(article);
	}
	
	// To get list of all articles
	@SuppressWarnings("unchecked")
	public List<Article> listArticles() {	
		System.out.println("dao_list");
		return (List<Article>) sessionFactory.getCurrentSession().createCriteria(Article.class).list();
	}
}