package com.americanbanksystems.wiki.service.implementation;

/*
 * 
 *  @author BorisM 
 *  @date   10.18.2014
 * 
 */

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.americanbanksystems.wiki.dao.ArticleDao;
import com.americanbanksystems.wiki.domain.Article;

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
		
		if (articleQuery.list().size() > 0) {
			return (Article) articleQuery.list().get(0);
		} else {
			return null;
		}		
		
	}
}
