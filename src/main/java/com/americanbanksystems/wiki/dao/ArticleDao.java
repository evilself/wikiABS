package com.americanbanksystems.wiki.dao;

/*
 * 
 *  @author BorisM 
 *  @date   10.18.2014
 * 
 */

import java.util.List;

import com.americanbanksystems.wiki.domain.Article;
import com.americanbanksystems.wiki.domain.Product;
import com.americanbanksystems.wiki.domain.User;
import com.americanbanksystems.wiki.service.GenericDAO;

//This interface specifies any other services related to Article, rather than the CRUD operations that we have set up in our
//GenericDAO
public interface ArticleDao extends GenericDAO<Article, Long> {	
	
	Article findArticle(Long id);
	List<Article> findArticlesByTag(String tag);
	List<Article> findArticlesByTagOrTitle(String tag);
	List<Article> findArticleByProduct(Product product);
	List<Article> findArticleByProduct(Product product, int size);
	List<Article> findArticleByKeyInDescription(String key);
	List<Article> findArticleByCreator(User creator);
}
