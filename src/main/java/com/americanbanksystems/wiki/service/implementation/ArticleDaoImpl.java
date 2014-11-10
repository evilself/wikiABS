package com.americanbanksystems.wiki.service.implementation;

/*
 * 
 *  @author BorisM 
 *  @date   10.18.2014
 * 
 */

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.HibernateInterceptor;
import org.springframework.stereotype.Repository;

import com.americanbanksystems.wiki.dao.ArticleDao;
import com.americanbanksystems.wiki.domain.Article;
import com.americanbanksystems.wiki.domain.Product;
import com.americanbanksystems.wiki.domain.User;

@Repository("articleDao") //Component in persistance layer for IoC
public class ArticleDaoImpl extends HibernateDao<Article, Long> implements ArticleDao{
		
	public boolean removeArticle(Article article) {
		Query articleQuery = currentSession().createQuery(
                "from Article a where a = :art");
		articleQuery.setParameter("art", article);            
 
        // ok, remove as usual
        removeEntity(article);
        return true;		
	}

	public boolean addArticle(Article article) {
		// TODO Auto-generated method stub
		addEntity(article);
		return true;
	}

	public boolean updateArticle(Article article) {
		updateEntity(article);
		return true;
	}

	public Article findArticle(Long id) {
		Query articleQuery = currentSession().createQuery(
                "from Article a where a.id = :art");
		articleQuery.setParameter("art", id); 
		
		Article a = null;
		
		if (articleQuery.list().size() > 0) {
			a = (Article) articleQuery.list().get(0);
			Hibernate.initialize(a.getAttachments());
		} else {
			
		}		
		return a;
	}

	@Override
	public List<Article> findArticlesByTag(String tag) {
		String query = "from Article art where art.tag like :tag";
		Query articleQuery = currentSession().createQuery(query);
								 articleQuery.setParameter("tag", "%"+tag+"%"); 
		List<Article> articleList = new ArrayList<Article>();
		
		articleList =articleQuery.list();
		for(Article a: articleList)		{
			Hibernate.initialize(a.getCreatedByUser());
			Hibernate.initialize(a.getProduct());
		}
		
			return articleList;
		
	}

	@Override
	public List<Article> findArticleByProduct(Product product) {
		String query = "from Article art where art.product = :prod";
		Query articleQuery = currentSession().createQuery(query);
								 articleQuery.setParameter("prod", product); 
		List<Article> articleList = new ArrayList<Article>();
		
		articleList =articleQuery.list();
		for(Article a: articleList)		{
		//	Hibernate.initialize(a.getCreatedByUser());
		//	Hibernate.initialize(a.getProduct());
		}
		
		return articleList;
		
	}

	@Override
	public List<Article> findArticleByKeyInDescription(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Article> findArticleByCreator(User creator) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
