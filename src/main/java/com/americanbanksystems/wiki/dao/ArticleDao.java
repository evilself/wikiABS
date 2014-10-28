package com.americanbanksystems.wiki.dao;

/*
 * 
 *  @author BorisM 
 *  @date   10.18.2014
 * 
 */

import com.americanbanksystems.wiki.domain.Article;
import com.americanbanksystems.wiki.service.GenericDAO;

//This interface specifies any other services related to Article, rather than the CRUD operations that we have set up in our
//GenericDAO
public interface ArticleDao extends GenericDAO<Article, Long> {	
	Article findArticle(Long id);
}
