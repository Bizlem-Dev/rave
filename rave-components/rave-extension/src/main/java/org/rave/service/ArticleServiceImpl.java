package org.rave.service;


import java.util.List;
import org.rave.dao.ArticleDao;
import org.rave.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("articleService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleDao articleDao;

	public ArticleServiceImpl() {
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addArticle(Article article) {
		System.out.println("service_add");
		articleDao.saveArticle(article);
	}

	public List<Article> listArticles() {
		System.out.println("service_list");
		
		
		return articleDao.listArticles();
	}

}